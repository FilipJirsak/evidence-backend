package net.czela.backend.evidence.rest.objednavky;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;

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
	public List<Objednavka> seznam() {
		return objednavkyService.getAll();
	}

	@Get("/{uuid}")
	public Objednavka get(String uuid) {
		return objednavkyService.get(uuid);
	}

	@Post
	@Status(HttpStatus.CREATED)
	public Objednavka create(Objednavka objednavka) {
		objednavkyService.append(objednavka);
		return objednavka;
	}

	@Put("/{uuid}")
	@Status(HttpStatus.NO_CONTENT)
	public void update(String uuid, Objednavka objednavka) {
		objednavka.setUuid(uuid);
		objednavkyService.update(objednavka);
	}

}
