package net.czela.backend.evidence.domain.akce;

import io.micronaut.security.utils.SecurityService;
import net.czela.backend.evidence.domain.netadmin.Uzivatel;
import net.czela.backend.evidence.domain.netadmin.UzivatelDao;
import net.czela.backend.evidence.domain.netadmin.UzivatelService;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Filip
 */
@Singleton
public class AkceService {
  private final Jdbi jdbi;
  private final UzivatelService uzivatelService;

  public AkceService(Jdbi jdbi, UzivatelService uzivatelService) {
    this.jdbi = jdbi;
    this.uzivatelService = uzivatelService;
  }

  public List<Akce> seznam() {
    return jdbi.withExtension(AkceDao.class, AkceDao::seznam);
  }

  public List<Akce> mojeAkce() {
    return jdbi.withExtension(AkceDao.class, dao -> dao.seznamUzivatele(uzivatelService.aktualniUzivatelId()));
  }

  public AkceDetail detail(int id) {
    AkceDetail detail = new AkceDetail();
    detail.setAkce(jdbi.withExtension(AkceDao.class, dao -> dao.detail(id)));
    detail.setPridely(jdbi.withExtension(AkceDao.class, dao -> dao.pridely(id)));
    detail.setSefove(jdbi.withExtension(AkceDao.class, dao -> dao.role(id, 1)));
    detail.setSchvalovatele(jdbi.withExtension(AkceDao.class, dao -> dao.role(id, 2)));
    return detail;
  }
}
