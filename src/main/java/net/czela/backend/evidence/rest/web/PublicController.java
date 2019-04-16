package net.czela.backend.evidence.rest.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * @author Filip Jirsák
 */
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/public")
public class PublicController {
	@Get("/pocet-clenu")
	public String pocetClenu() {
		return "2";
	}
}
