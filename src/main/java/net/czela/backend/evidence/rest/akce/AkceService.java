package net.czela.backend.evidence.rest.akce;

import com.orientechnologies.orient.core.sql.executor.OResultSet;
import net.czela.backend.evidence.config.orientdb.OrientDBService;
import net.czela.backend.evidence.data.orientdb.OrientDB;

import javax.inject.Singleton;

/**
 * @author Filip
 */
@Singleton
public class AkceService {
  private final OrientDBService orientdb;

  public AkceService(OrientDBService orientdb) {
    this.orientdb = orientdb;
  }

  @OrientDB
  public Object seznam() {
//    OResultSet resultSet = orientdb.query("SELECT * FROM Akce ORDER BY nazev");
    return null;
  }

}
