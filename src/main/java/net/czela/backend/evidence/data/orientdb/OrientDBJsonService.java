package net.czela.backend.evidence.data.orientdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.record.OElement;

import javax.inject.Singleton;
import java.io.IOException;

/**
 * @author Filip Jirsák
 */
@Singleton
public class OrientDBJsonService {
  private final ObjectMapper objectMapper;

  public OrientDBJsonService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  //region JSON
  public ObjectNode toJson(OElement element) {
    try {
      String json = element.toJSON();
      return (ObjectNode) objectMapper.readTree(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
