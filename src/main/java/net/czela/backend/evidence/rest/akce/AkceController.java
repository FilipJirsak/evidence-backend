package net.czela.backend.evidence.rest.akce;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;

/**
 * @author Filip
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/akce")
public class AkceController {

	private final AkceService akceService;

	public AkceController(AkceService akceService) {
		this.akceService = akceService;
	}

	@Get
	public List<Akce> seznam() {
		return akceService.getAll();
	}

}
