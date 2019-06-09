package net.czela.backend.evidence.rest.ciselniky;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * @author Filip
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/ciselnik")
public class CiselnikController {

	private final CiselnikService ciselnikService;

	public CiselnikController(CiselnikService ciselnikService) {
		this.ciselnikService = ciselnikService;
	}

	@Get("/zpusob-uhrady")
	public ArrayNode zpusobUhrady() {
		return ciselnikService.zpusobUhrady();
	}
}
