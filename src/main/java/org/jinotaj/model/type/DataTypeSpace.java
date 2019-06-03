package org.jinotaj.model.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Filip
 */
public class DataTypeSpace {
  private final Map<Object, DataType> types = new HashMap<>();
  private final Map<String, DataType> names = new HashMap<>();

  public DataType byType(Object type) {
    return Objects.requireNonNull(types.get(type));
  }

  public DataType byName(String name) {
    return Objects.requireNonNull(names.get(name));
  }

  public <T extends DataType> T register(Consumer<DataTypeBuilder> builderConsumer) {
    DataTypeBuilder builder = new DataTypeBuilder();
    builderConsumer.accept(builder);
    final DataType dataType = builder.build();

    if (dataType.getType() != null) {
      DataType previous = types.putIfAbsent(dataType.getType(), dataType);
      if (previous != null) {
        throw new IllegalArgumentException(String.format("Duplicite registration for type %s.", dataType.getType()));
      }
    }
    if (dataType.getName() != null) {
      DataType previous = names.putIfAbsent(dataType.getName(), dataType);
      if (previous != null) {
        throw new IllegalArgumentException(String.format("Duplicite registration for name %s.", dataType.getType()));
      }
    }
    return (T) dataType;
  }

  public DataTypeSpace withRegistrar(Consumer<DataTypeBuilder> builderConsumer) {
    register(builderConsumer);
    return this;
  }
}
