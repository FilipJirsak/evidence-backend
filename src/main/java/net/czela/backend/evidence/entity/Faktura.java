package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.OrientDBProperty;
import net.czela.backend.evidence.database.Vertex;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class Faktura extends AbstractEntity {
  private String uuid;
  private Long flexibeeId;

  @OrientDBProperty(readonly = true)
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Long getFlexibeeId() {
    return flexibeeId;
  }

  public void setFlexibeeId(Long flexibeeId) {
    this.flexibeeId = flexibeeId;
  }
}
