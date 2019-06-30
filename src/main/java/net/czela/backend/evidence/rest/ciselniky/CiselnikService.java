package net.czela.backend.evidence.rest.ciselniky;

import net.czela.backend.evidence.database.OrientDBService;
import net.czela.backend.evidence.database.OrientDB;

import javax.inject.Singleton;

/**
 * @author Filip
 */
@Singleton
public class CiselnikService {
  private final OrientDBService orientdb;

  public CiselnikService(OrientDBService orientdb) {
    this.orientdb = orientdb;
  }

  @OrientDB
  public Object zpusobUhrady() {
//    OResultSet resultSet = orientdb.query("SELECT * FROM ZpusobUhrady ORDER BY kod");
    return null;
  }
}
