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

  public ArrayNode getAll() {
    return null; //orientDB.query("SELECT * FROM VUzivatel ORDER BY prijmeni, jmeno, id");
  }

}
