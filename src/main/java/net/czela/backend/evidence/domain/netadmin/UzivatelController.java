package net.czela.backend.evidence.domain.netadmin;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;
import java.util.Optional;

/**
 * @author Filip
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/uzivatel")
public class UzivatelController {

  private final UzivatelService uzivatelService;

  public UzivatelController(UzivatelService uzivatelService) {
    this.uzivatelService = uzivatelService;
  }

  @Get("/info")
  public Optional<Uzivatel> info() {
    return uzivatelService.aktualniUzivatel();
  }

  @Get("/seznam")
  public List<Uzivatel> seznam() {
    return uzivatelService.seznamUzivatelu();
  }
}
