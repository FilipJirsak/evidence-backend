package net.czela.backend.evidence.rest.objednavky;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

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
	public ObjectNode get(String id) {
		return objednavkyService.detail(id);
	}

	@Post
	@Status(HttpStatus.CREATED)
	public ObjectNode create(ObjectNode json) {
		return objednavkyService.pridat(json);
	}

	@Put("/{id}")
	@Status(HttpStatus.NO_CONTENT)
	public ObjectNode update(String id, ObjectNode json) {
		return objednavkyService.aktualizovat(id, json);
	}

}
