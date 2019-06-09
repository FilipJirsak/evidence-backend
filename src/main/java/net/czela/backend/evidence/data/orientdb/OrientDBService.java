package net.czela.backend.evidence.data.orientdb;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;

import javax.crypto.spec.OAEPParameterSpec;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Filip Jirsák
 */
@Singleton
public class OrientDBService {
  private final OrientDBSource source;
  private final OrientDBJsonService jsonService;
  private final SecurityService securityService;

  public OrientDBService(OrientDBSource source, OrientDBJsonService jsonService, SecurityService securityService) {
    this.source = source;
    this.jsonService = jsonService;
    this.securityService = securityService;
  }

  public ODatabaseSession getDatabaseSession() {
    return source.getDatabaseSession();
  }

  //region New vertex
  public OVertex newVertex() {
    return getDatabaseSession().newVertex();
  }

  public OVertex newVertex(String type) {
    return getDatabaseSession().newVertex(type);
  }

  public OVertex newVertex(OClass type) {
    return getDatabaseSession().newVertex(type);
  }

  public OVertex newVertex(ObjectNode json) {
    return jsonService.updateFromJson(newVertex(), json);
  }

  public OVertex newVertex(String type, ObjectNode json) {
    return jsonService.updateFromJson(newVertex(type), json);
  }

  public OVertex newVertex(OClass type, ObjectNode json) {
    return jsonService.updateFromJson(newVertex(type), json);
  }
  //endregion

  //region New edge
  public OEdge newEdge(OVertex from, OVertex to) {
    return getDatabaseSession().newEdge(from, to);
  }

  public OEdge newEdge(String type, OVertex from, OVertex to) {
    return getDatabaseSession().newEdge(from, to, type);
  }

  public OEdge newEdge(OClass type, OVertex from, OVertex to) {
    return getDatabaseSession().newEdge(from, to, type);
  }

  public OEdge newEdge(OVertex from, OVertex to, ObjectNode json) {
    return jsonService.updateFromJson(newEdge(from, to), json);
  }

  public OEdge newEdge(String type, OVertex from, OVertex to, ObjectNode json) {
    return jsonService.updateFromJson(newEdge(type, from, to), json);
  }

  public OEdge newEdge(OClass type, OVertex from, OVertex to, ObjectNode json) {
    return jsonService.updateFromJson(newEdge(type, from, to), json);
  }
  //endregion

  //region By @id
  public Optional<OVertex> vertexById(String id) {
    OElement element = getDatabaseSession().load(new ORecordId(id));
    return element.asVertex();
  }

  public Optional<OVertex> vertexById(String id, String type) {
    OElement element = getDatabaseSession().load(new ORecordId(id));
    if (!equlasType(element, type)) {
      return Optional.empty();
    }
    return element.asVertex();
  }

  public Optional<OEdge> edgeById(String id) {
    OElement element = getDatabaseSession().load(new ORecordId(id));
    return element.asEdge();
  }

  public Optional<OEdge> edgeById(String id, String type) {
    OElement element = getDatabaseSession().load(new ORecordId(id));
    if (!equlasType(element, type)) {
      return Optional.empty();
    }
    return element.asEdge();
  }

  private static boolean equlasType(OElement element, String type) {
    return element.getSchemaType().map(OClass::getName).filter(type::equals).isPresent();
  }
  //endregion

  //region query
  public OResultSet query(String query, Map<String, Object> args) {
    return getDatabaseSession().query(query, args);
  }

  public OResultSet query(String query, Object... args) {
    return getDatabaseSession().query(query, args);
  }

  public Optional<OVertex> singleVertex(String query, Map<String, Object> args) {
    return query(query, args).vertexStream()
            .findFirst();
  }

  public Optional<OVertex> singleVertex(String query, Object... args) {
    return query(query, args).vertexStream()
            .findFirst();
  }

  public Optional<OEdge> singleEdge(String query, Map<String, Object> args) {
    return query(query, args).edgeStream()
            .findFirst();
  }

  public Optional<OEdge> singleEdge(String query, Object... args) {
    return query(query, args).edgeStream()
            .findFirst();
  }
  //endregion

  //region Current user
  @Secured(SecurityRule.IS_AUTHENTICATED)
  public OVertex currentUserVertex() {
    return securityService
            .username()
            .flatMap(username -> singleVertex("SELECT FROM VUzivatel WHERE login = ?", username))
            .get();
  }

  public OEdge connectToCurrentUser(OVertex vertex) {
    OEdge edge = vertex.addEdge(vertex);
    return edge;
  }

  public OEdge connectToCurrentUser(OVertex vertex, OClass type) {
    OEdge edge = vertex.addEdge(vertex, type);
    return edge;
  }

  public OEdge connectToCurrentUser(OVertex vertex, String type) {
    OEdge edge = vertex.addEdge(vertex, type);
    return edge;
  }
  //endregion
}
