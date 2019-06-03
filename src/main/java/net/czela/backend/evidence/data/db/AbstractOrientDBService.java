package net.czela.backend.evidence.data.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OElement;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Filip
 */
public abstract class AbstractOrientDBService {
  protected final ODatabasePool pool;
  protected final ObjectMapper objectMapper;

  public AbstractOrientDBService(ODatabasePool pool, ObjectMapper objectMapper) {
    this.pool = pool;
    this.objectMapper = objectMapper;
  }

  //region Session wrapper
  public void withSession(Consumer<ODatabaseSession> action) {
    try (ODatabaseSession session = pool.acquire()) {
      try {
        action.accept(session);
        session.commit();
      } catch (RuntimeException e) {
        session.rollback();
        throw e;
      }
    }
  }

  public <T> T inSession(Function<ODatabaseSession, T> function) {
    try (ODatabaseSession session = pool.acquire()) {
      try {
        T result = function.apply(session);
        session.commit();
        return result;
      } catch (RuntimeException e) {
        session.rollback();
        throw e;
      }
    }
  }
  //endregion

  //region JSON
  protected ObjectNode elementToJson(OElement element) {
    try {
      String json = element.toJSON();
      return (ObjectNode) objectMapper.readTree(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected <T extends OElement> T applyChanges(T element, ObjectNode data) {
    try {
      ObjectNode jsonValue = elementToJson(element);
      jsonValue.setAll(data);
      String json = objectMapper.writeValueAsString(jsonValue);
      element.fromJSON(json);
      return element;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
  //endregion
}
