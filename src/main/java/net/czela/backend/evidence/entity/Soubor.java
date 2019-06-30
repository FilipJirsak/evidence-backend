package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.OrientDBProperty;
import net.czela.backend.evidence.database.Vertex;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class Soubor extends AbstractEntity {
  private String uuid;
  private String mediaType;
  private String name;
  private String popis;
  private String netadmin;

  @OrientDBProperty(readonly = true)
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPopis() {
    return popis;
  }

  public void setPopis(String popis) {
    this.popis = popis;
  }

  public String getNetadmin() {
    return netadmin;
  }

  public void setNetadmin(String netadmin) {
    this.netadmin = netadmin;
  }
}
