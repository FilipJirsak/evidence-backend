package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class ZpusobUhrady extends AbstractEntity {
  private final String nazev;
  private final String kod;

  public ZpusobUhrady(String kod, String nazev) {
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
