package net.czela.backend.evidence.data.orientdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Filip Jirsák
 */
@Singleton
public class OrientDBJsonService {
  public static final Map<String, String> MAP_ID = Map.of("@rid", "_rid");
  private final ObjectMapper objectMapper;

  public OrientDBJsonService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  //region JSON
  public ObjectNode toJson(OElement element) {
    String json = element.toJSON();
    return processJsonObject(json);
  }

  public ObjectNode toJson(OResult result) {
    String json = result.toJSON();
    return processJsonObject(json);
  }

  public ArrayNode toJson(OResultSet resultSet) {
    ArrayNode arrayNode = objectMapper.createArrayNode();
    resultSet.stream()
            .map(this::toJson)
            .forEach(arrayNode::add);
    return arrayNode;
  }

  public ObjectNode toJson(OElement element, Map<String, String> propertyMapping) {
    String json = element.toJSON();
    return processJsonObject(json, propertyMapping);
  }

  public ObjectNode toJson(OResult result, Map<String, String> propertyMapping) {
    String json = result.toJSON();
    return processJsonObject(json, propertyMapping);
  }

  public ArrayNode toJson(OResultSet resultSet, Map<String, String> propertyMapping) {
    ArrayNode arrayNode = objectMapper.createArrayNode();
    resultSet.stream()
            .map(result -> toJson(result, propertyMapping))
            .forEach(arrayNode::add);
    return arrayNode;
  }

  public <T extends OElement> T fromJson(T element, ObjectNode data) {
    try {
      String json = objectMapper.writeValueAsString(data);
      element.fromJSON(json);
      return element;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public <T extends OElement> T updateFromJson(T element, ObjectNode data) {
    ObjectNode jsonValue = toJson(element);
    jsonValue.setAll(data);
    fromJson(element, data);
    return element;
  }

  private ObjectNode createJsonObject(String json) {
    ObjectNode objectNode;
    try {
      objectNode = (ObjectNode) objectMapper.readTree(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return objectNode;
  }

  private ObjectNode processJsonObject(String json) {
    ObjectNode objectNode = createJsonObject(json);
    filterObjectNode(objectNode);
    return objectNode;
  }

  private ObjectNode processJsonObject(String json, Map<String, String> propertyMapping) {
    ObjectNode objectNode = createJsonObject(json);
    filterObjectNode(objectNode, propertyMapping);
    return objectNode;
  }

  private void filterObjectNode(ObjectNode objectNode) {
    Iterator<String> names = objectNode.fieldNames();
    while (names.hasNext()) {
      String name = names.next();
      if (name.startsWith("@") && !name.equals("@version")) {
        names.remove();
      }
    }
  }

  private void filterObjectNode(ObjectNode objectNode, Map<String, String> propertyMapping) {
    for (Map.Entry<String, String> entry : propertyMapping.entrySet()) {
      String source = entry.getKey();
      String target = entry.getValue();
      if (target != null) {
        JsonNode propertyValue = objectNode.get(source);
        objectNode.set(target, propertyValue);
      }
      objectNode.remove(source);
    }
    filterObjectNode(objectNode);
  }

  //endregion

  //region Model
  public JsonNode model(OElement element) {
    try {
      String json = element.toJSON();
      return objectMapper.readTree(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ModelBuilder model() {
    return new ModelBuilder();
  }

  public class ModelBuilder {
    private final ObjectNode objectNode = objectMapper.createObjectNode();

    public ModelBuilder add(String name, OElement element) {
      objectNode.set(name, model(element));
      return this;
    }

    public ObjectNode get() {
      return objectNode;
    }
  }
  //endregion

}
