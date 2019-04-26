package net.czela.backend.evidence.rest.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import net.czela.backend.evidence.ucetnictvi.flexibee.FlexibeeFaktury;
import net.czela.backend.evidence.ucetnictvi.flexibee.FlexibeePage;
import org.dom4j.Document;

import java.util.Collections;
import java.util.List;

/**
 * @author Filip Jirsák
 */
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/public")
public class PublicController {
	private final FlexibeeFaktury faktury;

	public PublicController(FlexibeeFaktury faktury) {
		this.faktury = faktury;
	}


	@Get("/pocet-clenu")
	public String pocetClenu() {
		return "2";
	}

	@Get("/test")
	public String test() {
		Document result = faktury.prijate(List.of("84", "89"));
		return result.asXML();
	}
}
