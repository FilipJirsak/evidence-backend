package net.czela.backend.evidence.rest.dokumenty;

import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.MediaType;
import net.czela.backend.evidence.data.db.OrientDBService;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class SouborService {
	private final OrientDBService orientdb;
	private final Path uploadDir;

	public SouborService(OrientDBService orientdb, @Value("${evidence.upload-dir}") Path uploadDir) {
		this.orientdb = orientdb;
		this.uploadDir = uploadDir;
	}

	/*
	public SouborUUID vytvoritSoubor(Soubor soubor) {
		return orientdb.inSession(session -> {
			final OVertex vertex = session.newVertex("VSoubor");
			vertex.setProperty("mediaType", soubor.getMediaType());
			vertex.setProperty("name", soubor.getName());
			vertex.save();
			return new SouborUUID(vertex.getProperty("uuid"));
		});
	}

	public NovaFaktura vytvoritFakturu(Soubor souborInfo) {
		return orientdb.inSession(session -> {
			OVertex soubor = session.newVertex("VSoubor");
			soubor.setProperty("mediaType", souborInfo.getMediaType());
			soubor.setProperty("name", souborInfo.getName());
			soubor.save();

			OVertex dokument = session.newVertex("VDokument");
			dokument.save();

			OVertex faktura = session.newVertex("VFaktura");
			faktura.setProperty("flexibee_id", null);
			faktura.save();

			OEdge dokumetSouborHlavni = session.newEdge(dokument, soubor, "EDokumentSouborHlavni");
			dokumetSouborHlavni.save();

			OEdge fakturaDokument = session.newEdge(faktura, dokument, "EFakturaDokument");
			fakturaDokument.save();

			return new NovaFaktura(soubor.getProperty("uuid"), faktura.getProperty("kod"));
		});
	}

	public void ulozit(String uuid, InputStream stream) throws IOException {
		Files.copy(stream, uploadDir.resolve(uuid));
	}

	public File file(String uuid) {
		return uploadDir.resolve(uuid).toFile();
	}

	public Optional<MediaType> mediaType(String uuid) {
		return orientdb.inSession(session -> {
			try (OResultSet resultSet = session.query("SELECT mediaType FROM VSoubor WHERE uuid = ?", uuid)) {
				if (!resultSet.hasNext()) {
					return Optional.empty();
				}
				final OResult result = resultSet.next();
				return Optional.ofNullable((String) result.getProperty("mediaType"))
						.map(MediaType::of);
			}
		});
	}*/
}
