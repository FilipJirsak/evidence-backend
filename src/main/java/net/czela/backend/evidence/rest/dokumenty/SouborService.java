package net.czela.backend.evidence.rest.dokumenty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;
import io.micronaut.context.annotation.Value;
import net.czela.backend.evidence.data.orientdb.OrientDB;
import net.czela.backend.evidence.data.orientdb.OrientDBJsonService;
import net.czela.backend.evidence.data.orientdb.OrientDBService;

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
  private final OrientDBJsonService jsonService;
  private final Path uploadDir;

  public SouborService(@Value("${evidence.upload-dir}") Path uploadDir, OrientDBService orientdb, OrientDBJsonService jsonService) {
    this.orientdb = orientdb;
    this.uploadDir = uploadDir;
    this.jsonService = jsonService;
  }

  @OrientDB
  public JsonNode vytvoritSoubor(ObjectNode json) {
    ODatabaseSession databaseSession = orientdb.getDatabaseSession();
    OVertex soubor = orientdb.newVertex("VSoubor", json);
    orientdb.connectToCurrentUser(soubor, "EVlastnik");
    soubor.save();
    return jsonService.model(soubor);
  }

/*
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
*/

  public void ulozit(String uuid, InputStream stream) throws IOException {
    Files.copy(stream, uploadDir.resolve(uuid));
  }

  public File file(String uuid) {
    return uploadDir.resolve(uuid).toFile();
  }

  public Optional<JsonNode> mediaType(String uuid) {
    return orientdb
            .singleVertex("SELECT mediaType FROM VSoubor WHERE uuid = ?", uuid)
            .map(jsonService::model);
  }
}
