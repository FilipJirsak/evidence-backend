package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

import java.time.LocalDate;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class PridelAkci extends AbstractEntity {
  private Akce akce;
  private FormatovanyText popis;
  private LocalDate schvalen;
  private String odkaz;

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

  public LocalDate getSchvalen() {
    return schvalen;
  }

  public void setSchvalen(LocalDate schvalen) {
    this.schvalen = schvalen;
  }

  public String getOdkaz() {
    return odkaz;
  }

  public void setOdkaz(String odkaz) {
    this.odkaz = odkaz;
  }
}
