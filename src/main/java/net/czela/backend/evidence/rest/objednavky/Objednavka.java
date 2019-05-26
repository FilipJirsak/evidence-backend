package net.czela.backend.evidence.rest.objednavky;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Filip
 */
public class Objednavka {
  private String uuid;
  private String popis;
  private String dodavatel;
  private String ico;
  private String cisloUctu;
  private String variabilniSymbol;
  private BigDecimal castka;
  private Integer zpusobUhrady;
  private String datumObjednani;
  private String datumSplatnost;
  private String poznamka;
  private String akce;
  private String uhrazenoKym;
  private Boolean schvalena;
  private Integer stav;

  public Objednavka() {
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getPopis() {
    return popis;
  }

  public void setPopis(String popis) {
    this.popis = popis;
  }

  public String getDodavatel() {
    return dodavatel;
  }

  public void setDodavatel(String dodavatel) {
    this.dodavatel = dodavatel;
  }

  public String getIco() {
    return ico;
  }

  public void setIco(String ico) {
    this.ico = ico;
  }

  public String getCisloUctu() {
    return cisloUctu;
  }

  public void setCisloUctu(String cisloUctu) {
    this.cisloUctu = cisloUctu;
  }

  public String getVariabilniSymbol() {
    return variabilniSymbol;
  }

  public void setVariabilniSymbol(String variabilniSymbol) {
    this.variabilniSymbol = variabilniSymbol;
  }

  public BigDecimal getCastka() {
    return castka;
  }

  public void setCastka(BigDecimal castka) {
    this.castka = castka;
  }

  public Integer getZpusobUhrady() {
    return zpusobUhrady;
  }

  public void setZpusobUhrady(Integer zpusobUhrady) {
    this.zpusobUhrady = zpusobUhrady;
  }

  public String getDatumObjednani() {
    return datumObjednani;
  }

  public void setDatumObjednani(String datumObjednani) {
    this.datumObjednani = datumObjednani;
  }

  public String getDatumSplatnost() {
    return datumSplatnost;
  }

  public void setDatumSplatnost(String datumSplatnost) {
    this.datumSplatnost = datumSplatnost;
  }

  public String getPoznamka() {
    return poznamka;
  }

  public void setPoznamka(String poznamka) {
    this.poznamka = poznamka;
  }

  public String getAkce() {
    return akce;
  }

  public void setAkce(String akce) {
    this.akce = akce;
  }

  public String getUhrazenoKym() {
    return uhrazenoKym;
  }

  public void setUhrazenoKym(String uhrazenoKym) {
    this.uhrazenoKym = uhrazenoKym;
  }

  public Boolean getSchvalena() {
    return schvalena;
  }

  public void setSchvalena(Boolean schvalena) {
    this.schvalena = schvalena;
  }

  public Integer getStav() {
    return stav;
  }

  public void setStav(Integer stav) {
    this.stav = stav;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Objednavka that = (Objednavka) o;
    return uuid.equals(that.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}
