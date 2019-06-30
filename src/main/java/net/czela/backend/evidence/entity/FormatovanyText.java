package net.czela.backend.evidence.entity;

import io.micronaut.core.annotation.Introspected;
import net.czela.backend.evidence.database.Vertex;

/**
 * @author Filip
 */
@Introspected
@Vertex
public class FormatovanyText extends AbstractEntity {
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
