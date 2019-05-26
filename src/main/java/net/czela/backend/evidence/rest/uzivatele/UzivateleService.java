package net.czela.backend.evidence.rest.uzivatele;

import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Filip
 */
@Singleton
public class UzivateleService {
  private final List<Uzivatel> uzivatele = new LinkedList<>();

  public UzivateleService() {
    uzivatele.add(new Uzivatel("Filip", "Filip", "Jirsák"));
    uzivatele.add(new Uzivatel("chmelej", "Honza", "Chmelenský"));
    uzivatele.add(new Uzivatel("mcyrin", "Matěj", "Cyrín"));
    uzivatele.add(new Uzivatel("janastrnadova", "Jana", "Strnadová"));
    uzivatele.add(new Uzivatel("samson", "Petr", "Hájek", "Samson"));
    uzivatele.add(new Uzivatel("daman", "Tomáš", "Jeřábek", "Daman"));
  }

  public List<Uzivatel> getAll() {
    return uzivatele;
  }
}
