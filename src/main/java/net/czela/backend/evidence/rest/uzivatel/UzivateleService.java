package net.czela.backend.evidence.rest.uzivatel;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;
import io.micronaut.security.authentication.Authentication;
import net.czela.backend.evidence.data.db.OrientDBNativeService;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class UzivateleService {
  private final OrientDBNativeService orientDB;

  public UzivateleService(OrientDBNativeService orientDB) {
    this.orientDB = orientDB;
  }

  public ArrayNode getAll() {
    return orientDB.query("SELECT * FROM VUzivatel ORDER BY prijmeni, jmeno, id");
  }

  public Optional<OVertex> getCurrent(ODatabaseSession session, Authentication authentication) {
    return orientDB.nativeQueryVertexSingle("SELECT * FROM VUzivatel WHERE login = ?", authentication.getName());
  }
}
