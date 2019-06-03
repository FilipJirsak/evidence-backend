package org.jinotaj.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import org.jinotaj.model.value.DataValue;
import org.jinotaj.model.value.DataValueAtomic;
import org.jinotaj.model.value.DataValueCollectionIndexed;
import org.jinotaj.model.value.DataValueCollectionNamed;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Filip
 */
public class JacksonDataParser {
  private static final JacksonDataParser INSTANCE = new JacksonDataParser();

  private final ObjectMapper objectMapper;

  public JacksonDataParser(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public JacksonDataParser() {
    this(new ObjectMapper());
  }

  public static JacksonDataParser getInstance() {
    return INSTANCE;
  }

  public DataValue<?> parse(JsonParser parser) throws IOException {
    return parse((JsonNode) objectMapper.readTree(parser));
  }

  public DataValue<?> parse(File file) throws IOException {
    return parse(objectMapper.readTree(file));
  }

  public DataValue<?> parse(String content) throws IOException {
    return parse(objectMapper.readTree(content));
  }

  public DataValue<?> parse(byte[] content) throws IOException {
    return parse(objectMapper.readTree(content));
  }

  public DataValue<?> parse(InputStream inputStream) throws IOException {
    return parse(objectMapper.readTree(inputStream));
  }

  public DataValue<?> parse(Reader reader) throws IOException {
    return parse(objectMapper.readTree(reader));
  }

  public DataValue<?> parse(URL source) throws IOException {
    return parse(objectMapper.readTree(source));
  }

  private DataValue<?> parse(JsonNode jsonNode) {
    switch (jsonNode.getNodeType()) {
      case STRING:
        return parse((TextNode) jsonNode);
      case BOOLEAN:
        return parse((BooleanNode) jsonNode);
      case NUMBER:
        return parse((NumericNode) jsonNode);
      case NULL:
        return parse((NullNode) jsonNode);
      case OBJECT:
        return parse((ObjectNode) jsonNode);
      case ARRAY:
        return parse((ArrayNode) jsonNode);
      default:
        throw new IllegalArgumentException();
    }
  }

  private DataValueAtomic<Void> parse(NullNode jsonNode) {
    DataValueAtomic<Void> result = new DataValueAtomic<>(JacksonDataTypes.NULL);
    return result;
  }

  private DataValueAtomic<String> parse(TextNode jsonNode) {
    DataValueAtomic<String> result = new DataValueAtomic<>(JacksonDataTypes.STRING);
    result.set(jsonNode.textValue());
    return result;
  }

  private DataValueAtomic<Boolean> parse(BooleanNode jsonNode) {
    DataValueAtomic<Boolean> result = new DataValueAtomic<>(JacksonDataTypes.BOOLEAN);
    result.set(jsonNode.booleanValue());
    return result;
  }

  private DataValueAtomic<? extends Number> parse(NumericNode jsonNode) {
    switch (jsonNode.numberType()) {
      case INT:
        return parse((IntNode) jsonNode);
      case LONG:
        return parse((LongNode) jsonNode);
      case BIG_INTEGER:
        return parse((BigIntegerNode) jsonNode);
      case FLOAT:
        return parse((FloatNode) jsonNode);
      case DOUBLE:
        return parse((DoubleNode) jsonNode);
      case BIG_DECIMAL:
        return parse((DecimalNode) jsonNode);
      default:
        throw new IllegalArgumentException();
    }
  }

  private DataValueAtomic<Integer> parse(IntNode jsonNode) {
    DataValueAtomic<Integer> result = new DataValueAtomic<>(JacksonDataTypes.INTEGER);
    result.set(jsonNode.intValue());
    return result;
  }

  private DataValueAtomic<Long> parse(LongNode jsonNode) {
    DataValueAtomic<Long> result = new DataValueAtomic<>(JacksonDataTypes.LONG);
    result.set(jsonNode.longValue());
    return result;
  }

  private DataValueAtomic<BigInteger> parse(BigIntegerNode jsonNode) {
    DataValueAtomic<BigInteger> result = new DataValueAtomic<>(JacksonDataTypes.BIG_INTEGER);
    result.set(jsonNode.bigIntegerValue());
    return result;
  }

  private DataValueAtomic<Float> parse(FloatNode jsonNode) {
    DataValueAtomic<Float> result = new DataValueAtomic<>(JacksonDataTypes.FLOAT);
    result.set(jsonNode.floatValue());
    return result;
  }

  private DataValueAtomic<Double> parse(DoubleNode jsonNode) {
    DataValueAtomic<Double> result = new DataValueAtomic<>(JacksonDataTypes.DOUBLE);
    result.set(jsonNode.doubleValue());
    return result;
  }

  private DataValueAtomic<BigDecimal> parse(DecimalNode jsonNode) {
    DataValueAtomic<BigDecimal> result = new DataValueAtomic<>(JacksonDataTypes.BIG_DECIMAL);
    result.set(jsonNode.decimalValue());
    return result;
  }

  private <T extends DataValue<?>> DataValueCollectionNamed parse(ObjectNode jsonObject) {
    DataValueCollectionNamed<T> result = new DataValueCollectionNamed<>(JacksonDataTypes.OBJECT);
    Iterator<Map.Entry<String, JsonNode>> iterator = jsonObject.fields();
    while (iterator.hasNext()) {
      Map.Entry<String, JsonNode> item = iterator.next();
      String name = item.getKey();
      JsonNode jsonNode = item.getValue();
      T value = (T) parse(jsonNode);
      result.add(name, value);
    }
    return result;
  }

  private <T extends DataValue<?>> DataValueCollectionIndexed parse(ArrayNode jsonArray) {
    DataValueCollectionIndexed<T> result = new DataValueCollectionIndexed<>(JacksonDataTypes.ARRAY);
    for (JsonNode jsonNode : jsonArray) {
      T value = (T) parse(jsonNode);
      result.add(value);
    }
    return result;
  }
}
