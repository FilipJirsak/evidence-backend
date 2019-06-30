package net.czela.backend.evidence.entity;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.inject.Inject;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filip
 */
@MicronautTest
class EnumServiceTest {
  @Inject
  private EnumService enumService;

  @Test
  void testOrgany() {
    assertAll(
            () -> assertNotNull(enumService.organByKod("RADA")),
            () -> assertNotNull(enumService.organByKod("KI")),
            () -> assertNotNull(enumService.organByKod("KK"))
    );
  }
}