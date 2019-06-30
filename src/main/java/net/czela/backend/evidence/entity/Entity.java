package net.czela.backend.evidence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orientechnologies.orient.core.id.ORID;
import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.OrientDBProperty;

/**
 * @author Filip
 */
@Introspected
public interface Entity {
  @JsonProperty("@rid")
  @OrientDBProperty(name = "@rid", readonly = true)
  ORID getORID();

  @JsonProperty("@version")
  @OrientDBProperty(name = "@version", readonly = true)
  int getOVersion();

}
