package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

import java.time.LocalDate;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class Akce extends AbstractEntity {
  private String nazev;
  private FormatovanyText popis;
  private LocalDate schvalena;
  private String odkaz;
  private Integer netadmin;

  public String getNazev() {
    return nazev;
  }

  public void setNazev(String nazev) {
    this.nazev = nazev;
  }

  public FormatovanyText getPopis() {
    return popis;
  }

  public void setPopis(FormatovanyText popis) {
    this.popis = popis;
  }

  public LocalDate getSchvalena() {
    return schvalena;
  }

  public void setSchvalena(LocalDate schvalena) {
    this.schvalena = schvalena;
  }

  public String getOdkaz() {
    return odkaz;
  }

  public void setOdkaz(String odkaz) {
    this.odkaz = odkaz;
  }

  public Integer getNetadmin() {
    return netadmin;
  }

  public void setNetadmin(Integer netadmin) {
    this.netadmin = netadmin;
  }
}
