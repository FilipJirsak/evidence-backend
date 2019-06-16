package net.czela.backend.evidence.config.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.OVertex;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import net.czela.backend.evidence.data.orientdb.AbstractBean;
import net.czela.backend.evidence.data.orientdb.OrientEmbedded;
import net.czela.backend.evidence.data.orientdb.OrientProperty;
import net.czela.backend.evidence.data.orientdb.OrientVertex;
import net.czela.backend.evidence.util.DateUtils;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class OrientDBService {
  private final OrientDBSource source;

  public OrientDBService(OrientDBSource source) {
    this.source = source;
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

    BeanIntrospection<T> introspection = BeanIntrospection.getIntrospection(beanClass);
    T bean = introspection.instantiate();
    loadTo(element, bean, introspection);
    return Optional.of(bean);
  }

  public <T> Optional<T> read(String rid, Class<T> beanClass) {
    ORecordId orid = new ORecordId(rid);
    return read(orid, beanClass);
  }

  public <T> T create(T bean) {
    BeanIntrospection<T> introspection = BeanIntrospection.getIntrospection((Class<T>) bean.getClass());
    OElement element = createElement(bean, introspection);
    element.save();
    loadTo(element, bean, introspection);
    return bean;
  }

  private <T> OVertex createElement(T bean, BeanIntrospection<T> introspection) {
    String type = getType(introspection);
    ODatabaseSession session = source.getDatabaseSession();
    OVertex vertex = session.newVertex(type);
    updateFrom(bean, vertex, introspection);
    return vertex;
  }

  private <T> String getType(BeanIntrospection<T> introspection) {
    AnnotationValue<OrientVertex> beanAnnotation = introspection.getAnnotation(OrientVertex.class);
    if (beanAnnotation == null) {
      throw new IllegalArgumentException();
    }
    return beanAnnotation.get("type", String.class).orElse(introspection.getBeanType().getSimpleName());
  }

  public <T> T update(String rid, T bean) {
    ODatabaseSession session = source.getDatabaseSession();
    ORecord record = session.load(new ORecordId(rid));
    if (!(record instanceof OElement)) {
      //TODO
      throw new RuntimeException();
    }
    OElement element = (OElement) record;

    BeanIntrospection<T> introspection = BeanIntrospection.getIntrospection((Class<T>) bean.getClass());
    updateFrom(bean, element, introspection);
    element.save();
    loadTo(element, bean, introspection);
    return bean;
  }

  private <T> void loadTo(OElement element, T bean, BeanIntrospection<T> introspection) {
    for (BeanProperty<T, Object> property : introspection.getBeanProperties()) {
      String name;
      AnnotationValue<OrientProperty> propertyAnnotation = property.getAnnotation(OrientProperty.class);
      if (propertyAnnotation != null) {
        name = propertyAnnotation.getRequiredValue("name", String.class);
      } else {
        name = property.getName();
      }
      Object value = element.getProperty(name);
      if (property.getType().equals(LocalDate.class)) {
        value = DateUtils.toLocalDate((Date) value);
      } else if (property.hasAnnotation(OrientEmbedded.class)) {
        BeanIntrospection<Object> propertyBeanIntrospection = BeanIntrospection.getIntrospection(property.getType());
        AnnotationValue<OrientVertex> orientBeanAnnotation = propertyBeanIntrospection.getAnnotation(OrientVertex.class);
        if (orientBeanAnnotation != null) {
          OElement embedded = (OElement) value;
          value = property.get(bean);
          if (value == null) {
            value = propertyBeanIntrospection.instantiate();
          }
          loadTo(embedded, value, propertyBeanIntrospection);
        }
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
      AnnotationValue<OrientProperty> propertyAnnotation = property.getAnnotation(OrientProperty.class);
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
      } else if (property.hasAnnotation(OrientEmbedded.class)) {
        BeanIntrospection<Object> propertyBeanIntrospection = BeanIntrospection.getIntrospection(property.getType());
        AnnotationValue<OrientVertex> orientBeanAnnotation = propertyBeanIntrospection.getAnnotation(OrientVertex.class);
        if (orientBeanAnnotation != null) {
          value = createElement(value, propertyBeanIntrospection);
        }
      }
      element.setProperty(name, value);
    }
  }
}