package org.jinotaj.model.type;

import java.util.Map;
import java.util.Optional;

/**
 * @author Filip
 */
public abstract class DataType {
  private final String name;
  private final Object type;
//  private final boolean mandatory;
  private final boolean nullable;
  private final Map<String, Object> attributes;

  DataType(String name, Object type, /*boolean mandatory,*/ boolean nullable, Map<String, Object> attributes) {
    this.name = name;
    this.type = type;
//    this.mandatory = mandatory;
    this.nullable = nullable;
    this.attributes = attributes;
  }

  public String getName() {
    return name;
  }

  public Object getType() {
    return type;
  }

/*
  public boolean isMandatory() {
    return mandatory;
  }
*/

  public boolean isNullable() {
    return nullable;
  }

  public <T> Optional<T> getAttribute(String name) {
    return (Optional<T>) Optional.ofNullable(attributes.get(name));
  }

  public abstract boolean isCollection();

  public abstract boolean isAtomic();

}
