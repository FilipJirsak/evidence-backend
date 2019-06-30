package net.czela.backend.evidence.domain.netadmin;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.utils.SecurityService;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filip
 */
@MicronautTest
class UzivatelServiceTest {

  @Inject
  private UzivatelService uzivatelService;

  @Test
  void getAktualniUzivatel() {
    Optional<Uzivatel> result = uzivatelService.aktualniUzivatel();
    assertTrue(result.isPresent());
    Uzivatel uzivatel = result.get();
    assertAll(
            () -> assertEquals(1, uzivatel.getId()),
            () -> assertEquals("novak", uzivatel.getUsername()),
            () -> assertEquals("Jan", uzivatel.getJmeno()),
            () -> assertEquals("Novák", uzivatel.getPrijmeni()),
            () -> assertEquals("Jan Novák", uzivatel.getCeleJmeno()),
            () -> assertEquals("Jan Novák", uzivatel.getDlouheJmeno()),
            () -> assertEquals("JN", uzivatel.getInicialy())
    );
  }

  @MockBean(SecurityService.class)
  SecurityService securityService() {
    return new MockSecurityService();
  }

  private static class MockSecurityService implements SecurityService {
    @Override
    public Optional<String> username() {
      return Optional.of("novak");
    }

    @Override
    public Optional<Authentication> getAuthentication() {
      return Optional.empty();
    }

    @Override
    public boolean isAuthenticated() {
      return true;
    }

    @Override
    public boolean hasRole(String role) {
      return true;
    }

    @Override
    public boolean hasRole(String role, String rolesKey) {
      return true;
    }
  }
}