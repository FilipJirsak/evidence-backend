package net.czela.backend.evidence.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import net.czela.backend.evidence.database.OrientDB;
import net.czela.backend.evidence.database.OrientDBService;
import net.czela.backend.evidence.entity.Objednavka;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class ObjednavkyService {
  private final OrientDBService orientdb;

  public ObjednavkyService(OrientDBService orientdb) {
    this.orientdb = orientdb;
  }

  @OrientDB
  public ArrayNode seznam() {
//    OResultSet resultSet = orientdb.query("SELECT * FROM Objednavka");
//    return jsonService.toJson(resultSet);
    return null;
  }

  @OrientDB
  public Optional<Objednavka> detail(String id) {
    return orientdb.read(id, Objednavka.class);
  }

  @OrientDB
  public Objednavka pridat(Objednavka objednavka) {
    return orientdb.create(objednavka);
  }

  @OrientDB
  public Objednavka aktualizovat(String id, Objednavka objednavka) {
    return orientdb.update(id, objednavka);
  }
}
