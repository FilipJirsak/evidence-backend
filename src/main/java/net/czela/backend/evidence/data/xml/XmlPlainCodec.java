package net.czela.backend.evidence.data.xml;

import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.core.io.buffer.ByteBufferFactory;
import io.micronaut.core.type.Argument;
import io.micronaut.http.MediaType;
import io.micronaut.http.codec.CodecException;
import io.micronaut.http.codec.MediaTypeCodec;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Filip Jirsák
 */
@Singleton
@Deprecated
public class XmlPlainCodec implements MediaTypeCodec {
	private final Collection<MediaType> mediaTypes = Collections.singleton(MediaType.APPLICATION_XML_TYPE);

	@Override
	public <T> T decode(Argument<T> type, InputStream inputStream) throws CodecException {
		if (!type.getType().equals(Document.class)) {
			throw new CodecException("Error encoding object.");
		}
		try {
			SAXReader reader = new SAXReader();
			return (T) reader.read(inputStream);
		} catch (DocumentException e) {
			throw new CodecException("Error decoding input stream.", e);

		}
	}

	@Override
	public <T> void encode(T object, OutputStream outputStream) throws CodecException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			write(object, outputStream);
		} catch (IOException e) {
			throw new CodecException("Error encoding object", e);
		}
	}

	@Override
	public <T> byte[] encode(T object) throws CodecException {
		if (!(object instanceof Document)) {
			throw new CodecException("Error encoding object.");
		}
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			write(object, baos);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new CodecException("Error encoding object", e);
		}
	}

	@Override
	public <T> ByteBuffer encode(T object, ByteBufferFactory allocator) throws CodecException {
		byte[] bytes = encode(object);
		int len = bytes.length;

		return allocator.buffer(len, len).write(bytes);
	}

	protected void write(Object object, OutputStream outputStream) throws IOException {
		XMLWriter xmlWriter = new XMLWriter(outputStream);
		try {
			xmlWriter.write(object);

		} finally {
			xmlWriter.close();
		}
	}

	@Override
	public Collection<MediaType> getMediaTypes() {
		return mediaTypes;
	}

	@Override
	public boolean supportsType(Class<?> type) {
		return type.isAssignableFrom(Document.class);
	}
}
