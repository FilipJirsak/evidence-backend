package net.czela.backend.evidence.database;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.iterator.ORecordIteratorClass;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.record.impl.ODocument;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import net.czela.backend.evidence.config.orientdb.OrientDBInterceptor;
import net.czela.backend.evidence.config.orientdb.OrientDBSource;
import net.czela.backend.evidence.util.DateUtils;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author Filip
 */
@Singleton
public class OrientDBService {
  private final OrientDBSource source;
  private final OrientDBInterceptor interceptor;

  public OrientDBService(OrientDBSource source, OrientDBInterceptor interceptor) {
    this.source = source;
    this.interceptor = interceptor;
  }

  public <T> List<T> readClass(Class<T> beanClass) {
    BeanIntrospection<T> introspection = getIntrospection(beanClass);
    List<T> result = new ArrayList<>();
    readClass(beanClass, document -> {
      T bean = introspection.instantiate();
      loadTo(document, bean, introspection);
      result.add(bean);
    });
    return result;
  }

  public <T> void readClass(Class<T> beanClass, Consumer<ODocument> consumer) {
    ODatabaseSession session = source.getDatabaseSession();
    BeanIntrospection<T> introspection = getIntrospection(beanClass);
    String type = getType(introspection);

    ORecordIteratorClass<ODocument> documents = session.browseClass(type);
    for (ODocument document : documents) {
      consumer.accept(document);
    }
  }

  public <T> Optional<T> read(ORID rid, Class<T> beanClass) {
    ODatabaseSession session = source.getDatabaseSession();
    ORecord record = session.load(rid);
    if (record == null) {
      return Optional.empty();
    }
    if (!(record instanceof OElement)) {
      //TODO
      throw new RuntimeException();
    }
    OElement element = (OElement) record;

    BeanIntrospection<T> introspection = getIntrospection(beanClass);
    T bean = introspection.instantiate();
    loadTo(element, bean, introspection);
    return Optional.of(bean);
  }

  public <T> Optional<T> read(String rid, Class<T> beanClass) {
    ORecordId orid = new ORecordId(rid);
    return read(orid, beanClass);
  }

  public <T> T create(T bean) {
    BeanIntrospection<T> introspection = getIntrospection(bean);
    OElement element = createElement(bean, introspection);
    element.save();
    loadTo(element, bean, introspection);
    return bean;
  }

  public <T> T update(String rid, T bean) {
    ODatabaseSession session = source.getDatabaseSession();
    ORecord record = session.load(new ORecordId(rid));
    if (!(record instanceof OElement)) {
      //TODO
      throw new RuntimeException();
    }
    OElement element = (OElement) record;

    BeanIntrospection<T> introspection = getIntrospection(bean);
    updateFrom(bean, element, introspection);
    element.save();
    loadTo(element, bean, introspection);
    return bean;
  }

  public void inSession(Runnable runnable) {
    interceptor.inSession(runnable);
  }

  public <T> T withSession(Callable<T> callable) throws Exception {
    return interceptor.withSession(callable);
  }

  private <T> BeanIntrospection<T> getIntrospection(Class<T> beanClass) {
    return BeanIntrospection.getIntrospection(beanClass);
  }

  private <T> BeanIntrospection<T> getIntrospection(T bean) {
    return getIntrospection((Class<T>) bean.getClass());
  }

  private <T> OVertex createElement(T bean, BeanIntrospection<T> introspection) {
    String type = getType(introspection);
    ODatabaseSession session = source.getDatabaseSession();
    OVertex vertex = session.newVertex(type);
    updateFrom(bean, vertex, introspection);
    return vertex;
  }

  private <T> String getType(BeanIntrospection<T> introspection) {
    AnnotationValue<Vertex> vertexAnnotation = introspection.getAnnotation(Vertex.class);
    if (vertexAnnotation != null) {
      return vertexAnnotation.get("type", String.class).orElse(introspection.getBeanType().getSimpleName());
    }
    AnnotationValue<Edge> edgeAnnotation = introspection.getAnnotation(Edge.class);
    if (edgeAnnotation != null) {
      return edgeAnnotation.get("type", String.class).orElse(introspection.getBeanType().getSimpleName());
    }
    throw new IllegalArgumentException();
  }

  private <T> void loadTo(OElement element, T bean, BeanIntrospection<T> introspection) {
    for (BeanProperty<T, Object> property : introspection.getBeanProperties()) {
      String name;
      AnnotationValue<OrientDBProperty> propertyAnnotation = property.getAnnotation(OrientDBProperty.class);
      if (propertyAnnotation != null) {
        name = propertyAnnotation.getRequiredValue("name", String.class);
      } else {
        name = property.getName();
      }
      Object value = element.getProperty(name);
      if (property.getType().equals(LocalDate.class)) {
        value = DateUtils.toLocalDate((Date) value);
/*
      } else if (property.hasAnnotation(OrientEmbedded.class)) {
        BeanIntrospection<Object> propertyBeanIntrospection = BeanIntrospection.getIntrospection(property.getType());
        AnnotationValue<Vertex> orientBeanAnnotation = propertyBeanIntrospection.getAnnotation(Vertex.class);
        if (orientBeanAnnotation != null) {
          OElement embedded = (OElement) value;
          value = property.get(bean);
          if (value == null) {
            value = propertyBeanIntrospection.instantiate();
          }
          loadTo(embedded, value, propertyBeanIntrospection);
        }
*/
      } else if (value instanceof OElement) {
        OElement linked = (OElement) value;
        BeanIntrospection<Object> propertyBeanIntrospection = BeanIntrospection.getIntrospection(property.getType());
        value = propertyBeanIntrospection.instantiate();
        loadTo(linked, value, propertyBeanIntrospection);
      }
      property.convertAndSet(bean, value);
    }
  }

  private <T> void updateFrom(T bean, OElement element, BeanIntrospection<T> introspection) {
    for (BeanProperty<T, Object> property : introspection.getBeanProperties()) {
      String name;
      AnnotationValue<OrientDBProperty> propertyAnnotation = property.getAnnotation(OrientDBProperty.class);
      if (propertyAnnotation != null) {
        if (propertyAnnotation.getRequiredValue("readonly", Boolean.class)) {
          continue;
        }
        name = propertyAnnotation.getRequiredValue("name", String.class);
      } else {
        name = property.getName();
      }
      Object value = property.get(bean);
      if (property.getType().equals(LocalDate.class)) {
        value = DateUtils.toDate((LocalDate) value);
/*
      } else if (property.hasAnnotation(OrientEmbedded.class)) {
        BeanIntrospection<Object> propertyBeanIntrospection = BeanIntrospection.getIntrospection(property.getType());
        AnnotationValue<Vertex> orientBeanAnnotation = propertyBeanIntrospection.getAnnotation(Vertex.class);
        if (orientBeanAnnotation != null) {
          value = createElement(value, propertyBeanIntrospection);
        }
*/
      }
      element.setProperty(name, value);
    }
  }
}