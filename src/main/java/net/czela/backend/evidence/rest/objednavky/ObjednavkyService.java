package net.czela.backend.evidence.rest.objednavky;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import net.czela.backend.evidence.data.orientdb.OrientDB;
import net.czela.backend.evidence.data.orientdb.OrientDBJsonService;
import net.czela.backend.evidence.data.orientdb.OrientDBService;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class ObjednavkyService {
  private final OrientDBService orientdb;
  private final OrientDBJsonService jsonService;

  public ObjednavkyService(OrientDBService orientdb, OrientDBJsonService jsonService) {
    this.orientdb = orientdb;
    this.jsonService = jsonService;
  }

  @OrientDB
  public ArrayNode seznam() {
    OResultSet resultSet = orientdb.query("SELECT * FROM Objednavka");
    return jsonService.toJson(resultSet);
  }

  @OrientDB
  public ObjectNode detail(String id) {
    Optional<OVertex> objednavka = orientdb.vertexById(id, "Objednavka");
    if (objednavka.isEmpty()) {
      return null;
    }
    return jsonService.toJson(objednavka.get());
  }

  @OrientDB
  public ObjectNode pridat(ObjectNode json) {
    OVertex result = orientdb.newVertex("Objednavka", json);
    result.save();
    return jsonService.toJson(result);
  }

  @OrientDB
  public ObjectNode aktualizovat(String id, ObjectNode json) {
    Optional<OVertex> objednavka = orientdb.vertexById(id, "Objednavka");
    if (objednavka.isEmpty()) {
      return null;
    }
    OVertex result = jsonService.updateFromJson(objednavka.get(), json);
    result.save();
    return jsonService.toJson(result);
  }
}
