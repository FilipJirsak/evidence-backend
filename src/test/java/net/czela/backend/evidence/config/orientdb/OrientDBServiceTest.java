package net.czela.backend.evidence.config.orientdb;

import io.micronaut.test.annotation.MicronautTest;
import net.czela.backend.evidence.entity.Uzivatel;
import net.czela.backend.evidence.database.OrientDBService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filip
 */
@MicronautTest
class OrientDBServiceTest {
  @Inject
  OrientDBService orientDB;

  @Inject
  OrientDBSourceImpl orientDBSource;

  @BeforeEach
  void startSession() {
    orientDBSource.startSession();
  }

  @AfterEach
  void endSession() {
    orientDBSource.endSession();
  }

  @Test
  void testCreate() {
    Uzivatel uzivatel = new Uzivatel();
    uzivatel.setId(1);
    uzivatel.setJmeno("Franta Pepa");
    uzivatel.setPrijmeni("Jednička");
    uzivatel.setLogin("jednicka");

    Uzivatel result = orientDB.create(uzivatel);

    assertNotNull(uzivatel);
    assertAll(
            () -> assertEquals(1, result.getId()),
            () -> assertEquals("Franta Pepa", uzivatel.getJmeno()),
            () -> assertEquals("Jednička", uzivatel.getPrijmeni()),
            () -> assertEquals("jednicka", uzivatel.getLogin()),
            () -> assertNotNull(uzivatel.getORID()),
            () -> assertEquals(1, uzivatel.getOVersion())
    );
  }
}