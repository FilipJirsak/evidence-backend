package net.czela.backend.evidence.data.common;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.data.orientdb.OrientVertex;

/**
 * @author Filip
 */
@Introspected
@OrientVertex
public class FormatovanyText {
  private String text;
  private String format;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }
}
