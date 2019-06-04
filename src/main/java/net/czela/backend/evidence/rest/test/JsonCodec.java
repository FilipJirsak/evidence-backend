package net.czela.backend.evidence.rest.test;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.core.io.buffer.ByteBufferFactory;
import io.micronaut.core.type.Argument;
import io.micronaut.http.MediaType;
import io.micronaut.http.codec.CodecException;
import io.micronaut.http.codec.MediaTypeCodec;
import io.micronaut.jackson.codec.JsonMediaTypeCodec;

import javax.inject.Singleton;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Filip Jirsák
 */
//@Replaces(JsonMediaTypeCodec.class)
//@Singleton
public class JsonCodec implements MediaTypeCodec {
  private final Collection<MediaType> mediaTypes = Collections.singleton(MediaType.APPLICATION_JSON_TYPE);

  @Override
  public Collection<MediaType> getMediaTypes() {
    return mediaTypes;
  }

  @Override
  public <T> T decode(Argument<T> type, InputStream inputStream) throws CodecException {
    System.out.println(type);
    return null;
  }

  @Override
  public <T> void encode(T object, OutputStream outputStream) throws CodecException {

  }

  @Override
  public <T> byte[] encode(T object) throws CodecException {
    return new byte[0];
  }

  @Override
  public <T> ByteBuffer encode(T object, ByteBufferFactory allocator) throws CodecException {
    byte[] bytes = encode(object);
    return allocator.copiedBuffer(bytes);
  }
}
