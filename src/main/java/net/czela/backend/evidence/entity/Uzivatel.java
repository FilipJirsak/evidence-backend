package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class Uzivatel extends AbstractEntity {
  private int id;
  private String login;
  private String jmeno;
  private String prijmeni;
  private String prezdivka;
  private String email;
  private Integer variabilniSymbol;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getJmeno() {
    return jmeno;
  }

  public void setJmeno(String jmeno) {
    this.jmeno = jmeno;
  }

  public String getPrijmeni() {
    return prijmeni;
  }

  public void setPrijmeni(String prijmeni) {
    this.prijmeni = prijmeni;
  }

  public String getPrezdivka() {
    return prezdivka;
  }

  public void setPrezdivka(String prezdivka) {
    this.prezdivka = prezdivka;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getVariabilniSymbol() {
    return variabilniSymbol;
  }

  public void setVariabilniSymbol(Integer variabilniSymbol) {
    this.variabilniSymbol = variabilniSymbol;
  }
}
