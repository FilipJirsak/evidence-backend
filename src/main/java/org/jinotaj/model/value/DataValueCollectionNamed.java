package org.jinotaj.model.value;

import org.jinotaj.model.type.DataTypeCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Filip
 */
public class DataValueCollectionNamed<T extends DataValue<?>> extends DataValue<DataTypeCollection> {
  private final Map<String, T> values = new HashMap<>();
  public DataValueCollectionNamed(DataTypeCollection dataType) {
    super(dataType);
  }

  public T get(String name) {
    return values.get(name);
  }

  public void add(String name, T value) {
    this.values.put(name, value);
  }
}
