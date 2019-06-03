package org.jinotaj.model.type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Filip
 */
public class DataTypeBuilder {
  private String name;
  private Object type;
//  private boolean mandatory;
  private boolean nullable;
  private boolean collection;
  private boolean indexed;
  private boolean named;
  private final Map<String, Object> attributes = new HashMap<>();

  public DataTypeBuilder name(String name) {
    this.name = name;
    return this;
  }

  public DataTypeBuilder type(Object type) {
    this.type = type;
    return this;
  }

/*
  public DataTypeBuilder mandatory(boolean mandatory) {
    this.mandatory = mandatory;
    return this;
  }
*/

  public DataTypeBuilder nullable() {
    this.nullable = true;
    return this;
  }

  public DataTypeBuilder collection() {
    this.collection = true;
    return this;
  }

  public DataTypeBuilder indexed() {
    this.indexed = true;
    return this;
  }

  public DataTypeBuilder named() {
    this.named = true;
    return this;
  }

  public DataTypeBuilder attribute(String name, Object attribute) {
    this.attributes.putIfAbsent(name, attribute);
    return this;
  }

  DataType build() {
    if (collection) {
      return new DataTypeCollection(name, type, /*mandatory,*/ nullable, attributes, indexed, named);
    } else {
      return new DataTypeAtomic(name, type, /*mandatory,*/ nullable, attributes);
    }
  }

}
