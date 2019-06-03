package org.jinotaj.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.jinotaj.model.type.DataType;
import org.jinotaj.model.type.DataTypeAtomic;
import org.jinotaj.model.type.DataTypeCollection;
import org.jinotaj.model.type.DataTypeSpace;

/**
 * @author Filip
 */
public class JacksonDataTypes {
  private static final DataTypeSpace SPACE = new DataTypeSpace();
  public static final DataTypeAtomic STRING = SPACE.register(b -> b
          .type(JsonNodeType.STRING)
  );

  public static final DataTypeAtomic BOOLEAN = SPACE.register(b -> b
          .type(JsonNodeType.BOOLEAN)
  );

  public static final DataTypeAtomic INTEGER = SPACE.register(b -> b
          .type(JsonParser.NumberType.INT)
  );

  public static final DataTypeAtomic LONG = SPACE.register(b -> b
          .type(JsonParser.NumberType.LONG)
  );

  public static final DataTypeAtomic BIG_INTEGER = SPACE.register(b -> b
          .type(JsonParser.NumberType.BIG_INTEGER)
  );

  public static final DataTypeAtomic FLOAT = SPACE.register(b -> b
          .type(JsonParser.NumberType.FLOAT)
  );

  public static final DataTypeAtomic DOUBLE = SPACE.register(b -> b
          .type(JsonParser.NumberType.DOUBLE)
  );

  public static final DataTypeAtomic BIG_DECIMAL = SPACE.register(b -> b
          .type(JsonParser.NumberType.BIG_DECIMAL)
  );

  public static final DataTypeAtomic NULL = SPACE.register(b -> b
          .type(JsonNodeType.NULL)
  );

  public static final DataTypeCollection OBJECT = SPACE.register(b -> b
          .type(JsonNodeType.OBJECT)
          .collection()
          .named()
  );

  public static final DataTypeCollection ARRAY = SPACE.register(b -> b
          .type(JsonNodeType.ARRAY)
          .collection()
          .indexed()
  );

  public static DataTypeSpace getSpace() {
    return SPACE;
  }
}
