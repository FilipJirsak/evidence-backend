package net.czela.backend.evidence.rest.ciselniky;

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
public class CiselnikService {
  private final OrientDBService orientdb;
  private final OrientDBJsonService jsonService;

  public CiselnikService(OrientDBService orientdb, OrientDBJsonService jsonService) {
    this.orientdb = orientdb;
    this.jsonService = jsonService;
  }

  @OrientDB
  public ArrayNode zpusobUhrady() {
    OResultSet resultSet = orientdb.query("SELECT * FROM ZpusobUhrady ORDER BY kod");
    return jsonService.toJson(resultSet);
  }
}
