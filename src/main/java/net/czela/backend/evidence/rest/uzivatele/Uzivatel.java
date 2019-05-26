package net.czela.backend.evidence.rest.uzivatele;

import org.apache.commons.lang.text.StrBuilder;

/**
 * @author Filip
 */
public class Uzivatel {
  private String login;
  private String jmeno;
  private String prijmeni;
  private String prezdivka;

  public Uzivatel() {
  }

  public Uzivatel(String login, String jmeno, String prijmeni) {
    this.login = login;
    this.jmeno = jmeno;
    this.prijmeni = prijmeni;
  }

  public Uzivatel(String login, String jmeno, String prijmeni, String prezdivka) {
    this.login = login;
    this.jmeno = jmeno;
    this.prijmeni = prijmeni;
    this.prezdivka = prezdivka;
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

  public String getCeleJmeno() {
    StringBuilder builder = new StringBuilder();
    builder.append(jmeno);
    builder.append(' ');
    builder.append(prijmeni);
    if (prezdivka != null) {
      builder.append(' ');
      builder.append('(');
      builder.append(prezdivka);
      builder.append(')');
    }
    return builder.toString();
  }
}
