package net.czela.backend.evidence.data.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import javax.inject.Singleton;
import java.util.Map;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
@Deprecated
public class OrientDBNativeService extends AbstractOrientDBService {
  public OrientDBNativeService(ODatabasePool pool, ObjectMapper objectMapper) {
    super(pool, objectMapper);
  }

  //region New vertex
  public OVertex newVertex(ODatabaseSession session) {
    return session.newVertex();
  }

  public OVertex newVertex(ODatabaseSession session, String type) {
    return session.newVertex(type);
  }

  public OVertex newVertex(ODatabaseSession session, OClass type) {
    return session.newVertex(type);
  }

  public OVertex newVertex(ODatabaseSession session, ObjectNode data) {
    return applyChanges(session.newVertex(), data);
  }

  public OVertex newVertex(ODatabaseSession session, String type, ObjectNode data) {
    return applyChanges(session.newVertex(type), data);
  }

  public OVertex newVertex(ODatabaseSession session, OClass type, ObjectNode data) {
    return applyChanges(session.newVertex(type), data);
  }

  public OVertex newVertex() {
    return inSession(session -> this.newVertex(session).save());
  }

  public OVertex newVertex(String type) {
    return inSession(session -> this.newVertex(session, type).save());
  }

  public OVertex newVertex(OClass type) {
    return inSession(session -> this.newVertex(session, type).save());
  }

  public OVertex newVertex(ObjectNode data) {
    return inSession(session -> this.newVertex(session, data).save());
  }

  public OVertex newVertex(String type, ObjectNode data) {
    return inSession(session -> this.newVertex(session, type, data).save());
  }

  public OVertex newVertex(OClass type, ObjectNode data) {
    return inSession(session -> this.newVertex(session, type, data).save());
  }
  //endregion

  //region Query native
  public Optional<OVertex> queryVertexSingle(ODatabaseSession session, String query, Map<String, Object> args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetVertexSingle(resultSet);
    }
  }

  public Optional<OVertex> queryVertexSingle(ODatabaseSession session, String query, Object... args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetVertexSingle(resultSet);
    }
  }

  public Optional<OVertex> queryVertexSingle(String query, Map<String, Object> args) {
    return this.inSession(session -> this.queryVertexSingle(session, query, args));
  }

  public Optional<OVertex> queryVertexSingle(String query, Object... args) {
    return this.inSession(session -> this.queryVertexSingle(session, query, args));
  }

  public Optional<OEdge> queryEdgeSingle(ODatabaseSession session, String query, Map<String, Object> args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetEdgeSingle(resultSet);
    }
  }

  public Optional<OEdge> queryEdgeSingle(ODatabaseSession session, String query, Object... args) {
    try (OResultSet resultSet = session.query(query, args)) {
      return resultSetEdgeSingle(resultSet);
    }
  }

  public Optional<OEdge> queryEdgeSingle(String query, Map<String, Object> args) {
    return this.inSession(session -> this.queryEdgeSingle(session, query, args));
  }

  public Optional<OEdge> queryEdgeSingle(String query, Object... args) {
    return this.inSession(session -> this.queryEdgeSingle(session, query, args));
  }
  //endregion

  //region Implementation
  private Optional<OVertex> resultSetVertexSingle(OResultSet resultSet) {
    return resultSet.vertexStream().findFirst();
  }

  private Optional<OEdge> resultSetEdgeSingle(OResultSet resultSet) {
    return resultSet.edgeStream().findFirst();
  }
  //endregion

}
