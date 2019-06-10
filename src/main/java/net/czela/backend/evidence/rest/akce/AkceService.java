package net.czela.backend.evidence.rest.akce;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import net.czela.backend.evidence.data.orientdb.OrientDB;
import net.czela.backend.evidence.data.orientdb.OrientDBJsonService;
import net.czela.backend.evidence.data.orientdb.OrientDBService;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author Filip
 */
@Singleton
public class AkceService {
  private final OrientDBService orientdb;
  private final OrientDBJsonService jsonService;

  public AkceService(OrientDBService orientdb, OrientDBJsonService jsonService) {
    this.orientdb = orientdb;
    this.jsonService = jsonService;
  }

  @OrientDB
  public ArrayNode seznam() {
    OResultSet resultSet = orientdb.query("SELECT * FROM Akce ORDER BY nazev");
    return jsonService.toJson(resultSet, OrientDBJsonService.MAP_ID);
  }

}
