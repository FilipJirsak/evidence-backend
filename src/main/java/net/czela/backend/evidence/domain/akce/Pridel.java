package net.czela.backend.evidence.domain.akce;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Filip
 */
public class Pridel {
  private int id;
  private BigDecimal castka;
  private String schvalenoOdkaz;
  private LocalDate schvalenoDatum;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getCastka() {
    return castka;
  }

  public void setCastka(BigDecimal castka) {
    this.castka = castka;
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
}
