package org.jinotaj.model.type;

import java.util.Map;

/**
 * @author Filip
 */
public class DataTypeCollection extends DataType {
  private final boolean indexed;
  private final boolean named;

  DataTypeCollection(String name, Object type, /*boolean mandatory,*/ boolean nullable, Map<String, Object> attributes, boolean indexed, boolean named) {
    super(name, type, /*mandatory,*/ nullable, attributes);
    this.indexed = indexed;
    this.named = named;
  }

  @Override
  public boolean isAtomic() {
    return false;
  }

  @Override
  public boolean isCollection() {
    return true;
  }

  public boolean isIndexed() {
    return indexed;
  }

  public boolean isNamed() {
    return named;
  }
}
