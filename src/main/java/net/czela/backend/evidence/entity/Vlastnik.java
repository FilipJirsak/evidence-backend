package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Edge;

/**
 * @author Filip
 */
@Introspected
@Edge
public class Vlastnik extends AbstractEntity {
  private Uzivatel out;

  public Uzivatel getOut() {
    return out;
  }

  public void setOut(Uzivatel out) {
    this.out = out;
  }
}
