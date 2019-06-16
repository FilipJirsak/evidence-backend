package net.czela.backend.evidence.data.orientdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

/**
 * @author Filip
 */
@Introspected
public abstract class AbstractBean {
  private String rid;
  private int version;

  @JsonProperty("@rid")
  @OrientProperty(name = "@rid", readonly = true)
  public String getOrientRID() {
    return rid;
  }

  public void setOrientRID(String rid) {
    this.rid = rid;
  }

  @JsonProperty("@version")
  @OrientProperty(name = "@version", readonly = true)
  public int getOrientVersion() {
    return version;
  }

  public void setOrientVersion(int version) {
    this.version = version;
  }
}
