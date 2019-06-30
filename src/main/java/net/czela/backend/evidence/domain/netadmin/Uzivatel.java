package net.czela.backend.evidence.domain.netadmin;

/**
 * @author Filip
 */
public class Uzivatel {
  private int id;
  private short vs;
  private String jmeno;
  private String prijmeni;
  private String prezdivka;
  private String username;
  private String password;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public short getVs() {
    return vs;
  }

  public void setVs(short vs) {
    this.vs = vs;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Vrací celé jméno uživatele ve tvaru „Jméno Příjmení“ (bez přezdívky).
   * @return
   */
  public String getCeleJmeno() {
    StringBuilder builder = new StringBuilder();
    builder.append(jmeno);
    builder.append(' ');
    builder.append(prijmeni);
    return builder.toString();
  }

  /**
   * Vrací dlouhé jméno uživatele ve tvaru „Jméno Příjmení (Přezdívka)“.
   * @return
   */
  public String getDlouheJmeno() {
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

  /**
   * Vrací iniciály uživatele (např. „JP“).
   * @return
   */
  public String getInicialy() {
    StringBuilder builder = new StringBuilder();
    builder.append(jmeno.charAt(0));
    builder.append(prijmeni.charAt(0));
    return builder.toString();
  }
}
