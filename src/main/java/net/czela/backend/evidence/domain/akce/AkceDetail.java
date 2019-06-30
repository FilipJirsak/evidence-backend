package net.czela.backend.evidence.domain.akce;

import java.util.List;

/**
 * @author Filip
 */
public class AkceDetail {
  private Akce akce;
  private List<Integer> sefove;
  private List<Integer> schvalovatele;
  private List<Pridel> pridely;

  public Akce getAkce() {
    return akce;
  }

  public void setAkce(Akce akce) {
    this.akce = akce;
  }

  public List<Integer> getSefove() {
    return sefove;
  }

  public void setSefove(List<Integer> sefove) {
    this.sefove = sefove;
  }

  public List<Integer> getSchvalovatele() {
    return schvalovatele;
  }

  public void setSchvalovatele(List<Integer> schvalovatele) {
    this.schvalovatele = schvalovatele;
  }

  public List<Pridel> getPridely() {
    return pridely;
  }

  public void setPridely(List<Pridel> pridely) {
    this.pridely = pridely;
  }
}
