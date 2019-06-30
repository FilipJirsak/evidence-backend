package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class PraceNaAkci extends AbstractEntity {
  private Akce akce;
  private FormatovanyText popis;
  private Uzivatel uzivatel;
  private BigDecimal castka;
  private LocalDate datum;

  public Akce getAkce() {
    return akce;
  }

  public void setAkce(Akce akce) {
    this.akce = akce;
  }

  public FormatovanyText getPopis() {
    return popis;
  }

  public void setPopis(FormatovanyText popis) {
    this.popis = popis;
  }

  public Uzivatel getUzivatel() {
    return uzivatel;
  }

  public void setUzivatel(Uzivatel uzivatel) {
    this.uzivatel = uzivatel;
  }

  public BigDecimal getCastka() {
    return castka;
  }

  public void setCastka(BigDecimal castka) {
    this.castka = castka;
  }

  public LocalDate getDatum() {
    return datum;
  }

  public void setDatum(LocalDate datum) {
    this.datum = datum;
  }
}
