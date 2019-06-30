package net.czela.backend.evidence.domain.netadmin;

import io.micronaut.security.utils.SecurityService;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class UzivatelService {
  private final Jdbi jdbi;
  private final SecurityService securityService;

  public UzivatelService(Jdbi jdbi, SecurityService securityService) {
    this.jdbi = jdbi;
    this.securityService = securityService;
  }

  public Optional<Uzivatel> aktualniUzivatel() {
    return securityService.username()
            .map(username -> jdbi.withExtension(UzivatelDao.class, dao -> dao.uzivatelByUsername(username)));
  }

  public int aktualniUzivatelId() {
    return securityService.username()
            .map(username -> jdbi.withExtension(UzivatelDao.class, dao -> dao.idByUsername(username)))
            .orElseThrow();
  }

  public List<Uzivatel> seznamUzivatelu() {
    return jdbi.withExtension(UzivatelDao.class, dao -> dao.seznamUzivatelu());
  }

}
