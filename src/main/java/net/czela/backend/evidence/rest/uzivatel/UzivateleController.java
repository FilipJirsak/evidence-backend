package net.czela.backend.evidence.rest.uzivatel;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * @author Filip
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/uzivatele")
public class UzivateleController {

	private final UzivateleService uzivateleService;

	public UzivateleController(UzivateleService uzivateleService) {
		this.uzivateleService = uzivateleService;
	}

	@Get
	public ArrayNode seznam() {
		return uzivateleService.getAll();
	}

	@Get("/ja")
	public ObjectNode aktualni() {
		return uzivateleService.current();
	}

}
