package org.jinotaj.model.jackson;

import org.jinotaj.model.value.DataValue;
import org.jinotaj.model.value.DataValueAtomic;
import org.jinotaj.model.value.DataValueCollectionIndexed;
import org.jinotaj.model.value.DataValueCollectionNamed;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filip
 */
class JacksonDataParserTest {
  @Test
  void testObject() throws IOException {
    final DataValue<?> data = JacksonDataParser.getInstance()
            .parse(JacksonDataParserTest.class.getResource("object.json"));
    assertNotNull(data);
    assertAll();
  }

  @Test
  void testArray() throws IOException {
    final DataValue<?> data = JacksonDataParser.getInstance()
            .parse(JacksonDataParserTest.class.getResource("array.json"));

    assertNotNull(data);
    assertTrue(data.getType().isCollection());

    DataValueCollectionIndexed<?> collection = (DataValueCollectionIndexed<?>) data;
    assertAll(
            () -> {
              DataValue<?> item = collection.get(0);
              assertNotNull(item);
              assertEquals(JacksonDataTypes.INTEGER, item.getType());
              DataValueAtomic<Integer> value = item.asAtomic();
              assertEquals(1, value.get());
            },
            () -> {
              DataValue<?> item = collection.get(1);
              assertNotNull(item);
              assertEquals(JacksonDataTypes.INTEGER, item.getType());
              DataValueAtomic<Integer> value = item.asAtomic();
              assertEquals(2, value.get());
            },
            () -> {
              DataValue<?> item = collection.get(2);
              assertNotNull(item);
              assertEquals(JacksonDataTypes.NULL, item.getType());
            },
            () -> {
              DataValue<?> item = collection.get(3);
              assertNotNull(item);
              assertEquals(JacksonDataTypes.STRING, item.getType());
              DataValueAtomic<String> value = item.asAtomic();
              assertEquals("4", value.get());
            },
            () -> {
              DataValue<?> item = collection.get(4);
              assertNotNull(item);
              assertEquals(JacksonDataTypes.ARRAY, item.getType());
              DataValueCollectionIndexed<?> value = item.asCollectionIndexed();
              DataValue<?> innerItem = value.get(0);
              assertEquals(5, innerItem.asAtomic().get());
            },
            () -> {
              DataValue<?> item = collection.get(5);
              assertNotNull(item);
              assertEquals(JacksonDataTypes.OBJECT, item.getType());
              DataValueCollectionNamed<?> value = item.asCollectionNamed();
              DataValue<?> innerItem = value.get("item");
              assertEquals(6, innerItem.asAtomic().get());
            }
    );
  }

}