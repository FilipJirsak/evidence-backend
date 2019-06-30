package net.czela.backend.evidence.domain.akce;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;

/**
 * @author Filip
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/akce")
public class AkceController {

  private final AkceService akceService;

  public AkceController(AkceService akceService) {
    this.akceService = akceService;
  }

  @Get("/moje")
  public List<Akce> moje() {
    return akceService.mojeAkce();
  }

  @Get("/seznam")
  public List<Akce> seznam() {
    return akceService.seznam();
  }

  @Get("/{id}")
  public AkceDetail detail(int id) {
    return akceService.detail(id);
  }
}
