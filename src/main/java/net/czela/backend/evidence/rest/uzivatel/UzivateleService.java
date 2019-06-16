package net.czela.backend.evidence.rest.uzivatel;

import net.czela.backend.evidence.config.orientdb.OrientDBService;
import net.czela.backend.evidence.data.orientdb.OrientDB;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class UzivateleService {
  private final OrientDBService orientdb;

  public UzivateleService(OrientDBService orientdb) {
    this.orientdb = orientdb;
  }

  @OrientDB
  public Object getAll() {
//    OResultSet resultSet = orientdb.query("SELECT * FROM Uzivatel ORDER BY prijmeni, jmeno, id");
    return null;
  }

  @OrientDB
  public Optional<Object> current() {
//    return orientdb.currentUserVertex()
//            .map(result -> jsonService.toJson(result, OrientDBJsonService.MAP_ID));
    return Optional.empty();
  }

}
