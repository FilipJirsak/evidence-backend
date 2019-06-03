package net.czela.backend.evidence.data.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class OrientDBJsonService extends AbstractOrientDBService {

  public OrientDBJsonService(ODatabasePool pool, ObjectMapper objectMapper) {
    super(pool, objectMapper);
  }

  //region Query JSON
  public ArrayNode query(ODatabaseSession session, String query, Map<String, Object> args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetToJson(resultSet);
    }
  }

  public ArrayNode query(ODatabaseSession session, String query, Object... args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetToJson(resultSet);
    }
  }

  public ArrayNode query(String query, Map<String, Object> args) {
    return inSession(session -> this.query(session, query, args));
  }

  public ArrayNode query(String query, Object... args) {
    return inSession(session -> this.query(session, query, args));
  }

  public Optional<ObjectNode> querySingle(ODatabaseSession session, String query, Map<String, Object> args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetSingleToJson(resultSet);
    }
  }

  public Optional<ObjectNode> querySingle(ODatabaseSession session, String query, Object... args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetSingleToJson(resultSet);
    }
  }

  public Optional<ObjectNode> querySingle(String query, Map<String, Object> args) {
    return inSession(session -> this.querySingle(session, query, args));
  }

  public Optional<ObjectNode> querySingle(String query, Object... args) {
    return inSession(session -> this.querySingle(session, query, args));
  }
  //endregion

  //region New vertex
  public ObjectNode newVertex(ODatabaseSession session) {
    OVertex vertex = session.newVertex();
    return saveElement(vertex);
  }

  public ObjectNode newVertex(ODatabaseSession session, String type) {
    OVertex vertex = session.newVertex(type);
    return saveElement(vertex);
  }

  public ObjectNode newVertex(ODatabaseSession session, OClass type) {
    OVertex vertex = session.newVertex(type);
    return saveElement(vertex);
  }

  public ObjectNode newVertex(ODatabaseSession session, ObjectNode data) {
    OVertex vertex = session.newVertex();
    return saveElement(vertex, data);
  }

  public ObjectNode newVertex(ODatabaseSession session, String type, ObjectNode data) {
    OVertex vertex = session.newVertex(type);
    return saveElement(vertex, data);
  }

  public ObjectNode newVertex(ODatabaseSession session, OClass type, ObjectNode data) {
    OVertex vertex = session.newVertex(type);
    return saveElement(vertex, data);
  }

  public ObjectNode newVertex() {
    return this.inSession(session -> this.newVertex(session));
  }

  public ObjectNode newVertex(String type) {
    return this.inSession(session -> this.newVertex(session, type));
  }

  public ObjectNode newVertex(OClass type) {
    return this.inSession(session -> this.newVertex(session, type));
  }

  public ObjectNode newVertex(ObjectNode data) {
    return this.inSession(session -> this.newVertex(session, data));
  }

  public ObjectNode newVertex(String type, ObjectNode data) {
    return this.inSession(session -> this.newVertex(session, type, data));
  }

  public ObjectNode newVertex(OClass type, ObjectNode data) {
    return this.inSession(session -> this.newVertex(session, type, data));
  }

  private ObjectNode saveElement(OElement element) {
    ObjectNode jsonNode = elementToJson(element);
    element.save();
    return jsonNode;
  }

  private ObjectNode saveElement(OElement element, ObjectNode data) {
    applyChanges(element, data);
    ObjectNode jsonNode = elementToJson(element);
    element.save();
    return jsonNode;
  }
  //endregion

  //region Implementation
  private ObjectNode resultToJson(OResult result) {
    try {
      String json = result.toJSON();
      return (ObjectNode) objectMapper.readTree(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ArrayNode resultSetToJson(OResultSet resultSet) {
    ArrayNode arrayNode = objectMapper.createArrayNode();
    resultSet.stream()
            .map(this::resultToJson)
            .forEach(arrayNode::add);
    return arrayNode;
  }

  private Optional<ObjectNode> resultSetSingleToJson(OResultSet resultSet) {
    return resultSet.stream()
            .map(this::resultToJson)
            .findFirst();
  }
  //endregion
}
