package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Edge;

import java.time.ZonedDateTime;

/**
 * @author Filip
 */
@Introspected
@Edge
public class Schvaleno extends AbstractEntity {
  private Uzivatel out;
  private ZonedDateTime datum;

  public Uzivatel getOut() {
    return out;
  }

  public void setOut(Uzivatel out) {
    this.out = out;
  }

  public ZonedDateTime getDatum() {
    return datum;
  }

  public void setDatum(ZonedDateTime datum) {
    this.datum = datum;
  }
}
