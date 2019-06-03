package net.czela.backend.evidence.rest.dokumenty;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.micronaut.context.annotation.Value;
import io.micronaut.security.authentication.Authentication;
import net.czela.backend.evidence.data.db.OrientDBJsonService;
import net.czela.backend.evidence.data.db.OrientDBNativeService;
import net.czela.backend.evidence.rest.uzivatel.UzivateleService;

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
  private final OrientDBNativeService orientDbNative;
  private final OrientDBJsonService orientDbJson;
  private UzivateleService uzivatel;
  private final Path uploadDir;

  public SouborService(
          OrientDBNativeService orientDbNative,
          OrientDBJsonService orientDbJson,
          UzivateleService uzivatel,
          @Value("${evidence.upload-dir}") Path uploadDir) {
    this.orientDbNative = orientDbNative;
    this.orientDbJson = orientDbJson;
    this.uzivatel = uzivatel;
    this.uploadDir = uploadDir;
  }

  public ObjectNode vytvoritSoubor(ObjectNode json, Authentication authentication) {
  	//TODO
    orientDbJson.inSession(session ->
            orientDbNative.newVertex(session, "VSoubor", json)
                    .addEdge(uzivatel.getCurrent(session, authentication).get(), "EVlastnik")
                    .save()
    );

    return null;
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

  public Optional<ObjectNode> mediaType(String uuid) {
    return orientDbJson.querySingle("SELECT mediaType FROM VSoubor WHERE uuid = ?", uuid);
  }
}
