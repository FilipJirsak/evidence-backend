package net.czela.backend.evidence.rest.moje;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;

/**
 * @author Filip Jirsák
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/moje")
public class MojeController {

	@Get
	public String index(Authentication authentication) {
		return authentication.getName();
	}


	@Secured("root")
	@Get("/root")
	public String root() {
		return "root";
	}

	@Secured("admin")
	@Get("/admin")
	public String admin() {
		return "admin";
	}

	@Secured("guest")
	@Get("/guest")
	public String guest() {
		return "guest";
	}
}
