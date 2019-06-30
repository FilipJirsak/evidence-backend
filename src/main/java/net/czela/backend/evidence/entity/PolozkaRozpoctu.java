package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class PolozkaRozpoctu extends AbstractEntity {
  private String nazev;
  private BigDecimal castka;
  private Organ organ;
  private List<PolozkaRozpoctu> podkapitoly;

  public String getNazev() {
    return nazev;
  }

  public void setNazev(String nazev) {
    this.nazev = nazev;
  }

  public BigDecimal getCastka() {
    return castka;
  }

  public void setCastka(BigDecimal castka) {
    this.castka = castka;
  }

  public Organ getOrgan() {
    return organ;
  }

  public void setOrgan(Organ organ) {
    this.organ = organ;
  }

  public List<PolozkaRozpoctu> getPodkapitoly() {
    return podkapitoly;
  }

  public void setPodkapitoly(List<PolozkaRozpoctu> podkapitoly) {
    this.podkapitoly = podkapitoly;
  }
}
