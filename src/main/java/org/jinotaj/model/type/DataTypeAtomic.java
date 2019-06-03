package org.jinotaj.model.type;

import java.util.Map;

/**
 * @author Filip
 */
public class DataTypeAtomic extends DataType {

  DataTypeAtomic(String name, Object type, /*boolean mandatory,*/ boolean nullable, Map<String, Object> attributes) {
    super(name, type, /*mandatory,*/ nullable, attributes);
  }

  @Override
  public boolean isAtomic() {
    return true;
  }

  @Override
  public boolean isCollection() {
    return false;
  }

}
