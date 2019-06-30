package net.czela.backend.evidence.domain.akce;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author Filip
 */
public class Akce {
  private int id;
  private String nazev;
  private String schvalenoOdkaz;
  private LocalDate schvalenoDatum;
  private BigDecimal castka;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNazev() {
    return nazev;
  }

  public void setNazev(String nazev) {
    this.nazev = nazev;
  }

  public String getSchvalenoOdkaz() {
    return schvalenoOdkaz;
  }

  public void setSchvalenoOdkaz(String schvalenoOdkaz) {
    this.schvalenoOdkaz = schvalenoOdkaz;
  }

  public LocalDate getSchvalenoDatum() {
    return schvalenoDatum;
  }

  public void setSchvalenoDatum(LocalDate schvalenoDatum) {
    this.schvalenoDatum = schvalenoDatum;
  }

  public BigDecimal getCastka() {
    return castka;
  }

  public void setCastka(BigDecimal castka) {
    this.castka = castka;
  }
}
