package net.czela.backend.evidence.rest.uzivatel;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
public class UzivateleService {
  private final OrientDBService orientdb;
  private final OrientDBJsonService jsonService;

  public UzivateleService(OrientDBService orientdb, OrientDBJsonService jsonService) {
    this.orientdb = orientdb;
    this.jsonService = jsonService;
  }

  @OrientDB
  public ArrayNode getAll() {
    OResultSet resultSet = orientdb.query("SELECT * FROM Uzivatel ORDER BY prijmeni, jmeno, id");
    return jsonService.toJson(resultSet, OrientDBJsonService.MAP_ID);
  }

  @OrientDB
  public Optional<ObjectNode> current() {
    return orientdb.currentUserVertex()
            .map(result -> jsonService.toJson(result, OrientDBJsonService.MAP_ID));
  }

}
