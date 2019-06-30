package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

import java.util.List;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class Rozpocet extends AbstractEntity {
  private int rok;
  private List<PolozkaRozpoctu> polozky;

  public int getRok() {
    return rok;
  }

  public void setRok(int rok) {
    this.rok = rok;
  }

  public List<PolozkaRozpoctu> getPolozky() {
    return polozky;
  }

  public void setPolozky(List<PolozkaRozpoctu> polozky) {
    this.polozky = polozky;
  }
}
