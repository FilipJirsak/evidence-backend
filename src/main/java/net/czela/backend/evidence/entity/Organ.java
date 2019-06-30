package net.czela.backend.evidence.entity;

import com.orientechnologies.orient.core.id.ORID;
import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class Organ  extends AbstractEntity {
  private final String kod;
  private final String nazev;

  Organ(String kod, String nazev) {
    this.kod = kod;
    this.nazev = nazev;
  }

  public String getNazev() {
    return nazev;
  }

  public String getKod() {
    return kod;
  }
}
