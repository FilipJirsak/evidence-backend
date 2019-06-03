package org.jinotaj.model.value;

import org.jinotaj.model.type.DataTypeAtomic;
import org.jinotaj.model.type.DataTypeCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip
 */
public class DataValueCollectionIndexed<T extends DataValue<?>> extends DataValue<DataTypeCollection> {
  private final List<T> values = new ArrayList<>();
  public DataValueCollectionIndexed(DataTypeCollection dataType) {
    super(dataType);
  }

  public T get(int index) {
    return values.get(index);
  }

  public void add(T value) {
    this.values.add(value);
  }
}
