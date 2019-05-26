package net.czela.backend.evidence.rest.objednavky;

import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author Filip
 */
@Singleton
public class ObjednavkyService {
  private final List<Objednavka> objednavky = new LinkedList<>();

  public List<Objednavka> getAll() {
    return objednavky;
  }

  public Objednavka get(String uuid) {
    return objednavky.stream().filter(objednavka -> objednavka.getUuid().equals(uuid)).findFirst().orElse(null);
  }

  public void append(Objednavka objednavka) {
    objednavka.setUuid(UUID.randomUUID().toString());
    objednavky.add(objednavka);
  }

  public void update(Objednavka objednavka) {
    int index = objednavky.indexOf(objednavka);
    if (index >= 0) {
      objednavky.set(index, objednavka);
    }
  }
}
