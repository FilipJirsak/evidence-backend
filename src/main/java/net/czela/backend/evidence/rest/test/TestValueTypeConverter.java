package net.czela.backend.evidence.rest.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Filip Jirsák
 */
@Singleton
public class TestValueTypeConverter extends StdDeserializer<TestValue> {
  private final ObjectMapper mapper;

  public TestValueTypeConverter() {
    super(TestValue.class);
    this.mapper = new ObjectMapper();
  }

  @Override
  public TestValue deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    TreeNode treeNode = mapper.readTree(parser);
    return new TestValue();
  }
}
