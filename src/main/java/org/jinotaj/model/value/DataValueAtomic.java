package org.jinotaj.model.value;

import org.jinotaj.model.type.DataType;
import org.jinotaj.model.type.DataTypeAtomic;

/**
 * @author Filip
 */
public class DataValueAtomic<T> extends DataValue<DataTypeAtomic> {
  private T value;
  public DataValueAtomic(DataTypeAtomic dataType) {
    super(dataType);
  }

  public T get() {
    return value;
  }

  public void set(T value) {
    this.value = value;
  }
}
