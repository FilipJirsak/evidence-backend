package net.czela.backend.evidence.rest.dokumenty;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.types.files.SystemFile;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Optional;

/**
 * @author Filip
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/soubor")
public class SouborController {

	private final SouborService souborService;

	public SouborController(SouborService souborService) {
		this.souborService = souborService;
	}

	@Post(value = "/novy")
	public HttpResponse<SouborUUID> novy(Soubor soubor) throws IOException {
		final SouborUUID uuid = souborService.vytvoritSoubor(soubor);
		return HttpResponse.ok(uuid);
	}

	@Post(value = "/nova-faktura")
	public HttpResponse<NovaFaktura> novaFaktura(Soubor soubor) throws IOException {
		final NovaFaktura faktura = souborService.vytvoritFakturu(soubor);
		return HttpResponse.ok(faktura);
	}

	@Put(value = "/upload/{uuid}", consumes = MediaType.ALL)
	public HttpResponse<?> upload(String uuid, @Body byte[] data) throws IOException {
		try (ByteArrayInputStream stream = new ByteArrayInputStream(data)) {
			souborService.ulozit(uuid, stream);
		}
		return HttpResponse.created(URI.create("https://www.czela.net/evidence/soubor/" + uuid));
	}

	@Get(value = "/{uuid}")
	public SystemFile download(String uuid) throws IOException {
		final Optional<MediaType> mediaType = souborService.mediaType(uuid);
		if (mediaType.isPresent()) {
			return new SystemFile(souborService.file(uuid), mediaType.get());
		}
		return new SystemFile(souborService.file(uuid));
	}
}
