package net.czela.backend.evidence.rest.akce;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

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
	public Object seznam() {
		return akceService.seznam();
	}

}
