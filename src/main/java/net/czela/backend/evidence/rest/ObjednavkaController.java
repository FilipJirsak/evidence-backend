package net.czela.backend.evidence.rest;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import net.czela.backend.evidence.entity.Objednavka;
import net.czela.backend.evidence.service.ObjednavkyService;

import java.util.Optional;

/**
 * @author Filip
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/objednavky")
public class ObjednavkaController {

	private final ObjednavkyService objednavkyService;

	public ObjednavkaController(ObjednavkyService objednavkyService) {
		this.objednavkyService = objednavkyService;
	}

	@Get
	public ArrayNode vsechny() {
		return objednavkyService.seznam();
	}

	@Get("/{id}")
	public Optional<Objednavka> get(String id) {
		return objednavkyService.detail(id);
	}

	@Post
	@Status(HttpStatus.CREATED)
	public Objednavka create(Objednavka objednavka) {
		return objednavkyService.pridat(objednavka);
	}

	@Put("/{id}")
	public Objednavka update(String id, Objednavka objednavka) {
		return objednavkyService.aktualizovat(id, objednavka);
	}

}
