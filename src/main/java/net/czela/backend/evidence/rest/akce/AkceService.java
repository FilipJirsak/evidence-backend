package net.czela.backend.evidence.rest.akce;

import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Filip
 */
@Singleton
public class AkceService {
  private final List<Akce> akce = new LinkedList<>();

  public AkceService() {
    akce.add(new Akce("1", "czela.fest 2019"));
    akce.add(new Akce("2", "czela.čáry 2019"));
    akce.add(new Akce("3", "czela.žranice 2019"));
    akce.add(new Akce("4", "Optika Čelákovice–Frankfurt n. M."));
  }

  public List<Akce> getAll() {
    return akce;
  }
}
