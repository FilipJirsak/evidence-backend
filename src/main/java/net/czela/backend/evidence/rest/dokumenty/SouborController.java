package net.czela.backend.evidence.rest.dokumenty;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import org.reactivestreams.Publisher;

import java.io.File;
import java.io.IOException;

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

	@Post(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA)
	public Single<HttpResponse<SouborUUID>> upload(StreamingFileUpload file) throws IOException {
		final SouborUUID uuid = souborService.vytvoritSoubor();

		File tempFile = File.createTempFile("upload", "tmp");
		Publisher<Boolean> uploadPublisher = file.transferTo(tempFile);
		return Single.fromPublisher(uploadPublisher)
				.map(success -> {
					if (success) {
						return HttpResponse.ok(uuid);
					} else {
						return HttpResponse.<SouborUUID>status(HttpStatus.CONFLICT)
								.body(uuid);
					}
				});
	}
}
