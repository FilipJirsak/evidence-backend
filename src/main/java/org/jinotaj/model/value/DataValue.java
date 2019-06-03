package org.jinotaj.model.value;

import org.jinotaj.model.type.DataType;

/**
 * @author Filip
 */
public class DataValue<T extends DataType> {
  private final T dataType;

  public DataValue(T dataType) {
    this.dataType = dataType;
  }

  public T getType() {
    return dataType;
  }

  public <S> DataValueAtomic<S> asAtomic() {
    return (DataValueAtomic<S>) this;
  }

  public <S extends DataValue<?>> DataValueCollectionIndexed<S> asCollectionIndexed() {
    return (DataValueCollectionIndexed<S>) this;
  }

  public <S extends DataValue<?>> DataValueCollectionNamed<S> asCollectionNamed() {
    return (DataValueCollectionNamed<S>) this;
  }
}
