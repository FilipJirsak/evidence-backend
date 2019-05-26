package net.czela.backend.evidence.rest.akce;

/**
 * @author Filip
 */
public class Akce {
  private String value;
  private String text;

  public Akce() {
  }

  public Akce(String value, String text) {
    this.value = value;
    this.text = text;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
