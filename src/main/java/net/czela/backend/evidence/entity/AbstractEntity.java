package net.czela.backend.evidence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orientechnologies.orient.core.id.ORID;
import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.OrientDBProperty;

/**
 * @author Filip
 */
public abstract class AbstractEntity implements Entity {
  private ORID rid;
  private int version;

  @Override
  public ORID getORID() {
    return rid;
  }

  public void setORID(ORID rid) {
    this.rid = rid;
  }

  @Override
  public int getOVersion() {
    return version;
  }

  public void setOVersion(int version) {
    this.version = version;
  }
}
