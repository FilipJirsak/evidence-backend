package net.czela.backend.evidence.data.objednavka;

import net.czela.backend.evidence.data.orientdb.AbstractBean;
import net.czela.backend.evidence.data.orientdb.OrientVertex;
import net.czela.backend.evidence.data.orientdb.OrientEmbedded;
import net.czela.backend.evidence.data.common.FormatovanyText;
import net.czela.backend.evidence.data.uzivatel.Uzivatel;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Filip
 */
@OrientVertex
public class Objednavka extends AbstractBean {
  private String popis;
  private BigDecimal castka;
  private LocalDate datumObjednani;
  private LocalDate datumSplatnosti;
  private ZpusobUhrady zpusobUhrady;
  private Uzivatel uhrazenoKym;
  private String dodavatel;
  private String ico;
  private String cisloUctu;
  private String variabilniSymbol;
  private FormatovanyText poznamka;

  public String getPopis() {
    return popis;
  }

  public void setPopis(String popis) {
    this.popis = popis;
  }

  public BigDecimal getCastka() {
    return castka;
  }

  public void setCastka(BigDecimal castka) {
    this.castka = castka;
  }

  public LocalDate getDatumObjednani() {
    return datumObjednani;
  }

  public void setDatumObjednani(LocalDate datumObjednani) {
    this.datumObjednani = datumObjednani;
  }

  public LocalDate getDatumSplatnosti() {
    return datumSplatnosti;
  }

  public void setDatumSplatnosti(LocalDate datumSplatnosti) {
    this.datumSplatnosti = datumSplatnosti;
  }

  public ZpusobUhrady getZpusobUhrady() {
    return zpusobUhrady;
  }

  public void setZpusobUhrady(ZpusobUhrady zpusobUhrady) {
    this.zpusobUhrady = zpusobUhrady;
  }

  public Uzivatel getUhrazenoKym() {
    return uhrazenoKym;
  }

  public void setUhrazenoKym(Uzivatel uhrazenoKym) {
    this.uhrazenoKym = uhrazenoKym;
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

  @OrientEmbedded
  public FormatovanyText getPoznamka() {
    return poznamka;
  }

  public void setPoznamka(FormatovanyText poznamka) {
    this.poznamka = poznamka;
  }
}