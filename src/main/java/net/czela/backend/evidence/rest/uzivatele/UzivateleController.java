package net.czela.backend.evidence.rest.uzivatele;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.types.files.SystemFile;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import net.czela.backend.evidence.rest.dokumenty.SouborService;
import net.czela.backend.evidence.rest.dokumenty.SouborUUID;
import net.czela.backend.evidence.rest.soubor.NovaFaktura;
import net.czela.backend.evidence.rest.soubor.Soubor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
	public List<Uzivatel> seznam() {
		return uzivateleService.getAll();
	}

}
