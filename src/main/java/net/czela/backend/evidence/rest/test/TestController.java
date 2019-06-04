package net.czela.backend.evidence.rest.test;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * @author Filip Jirsák
 */
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/test")
public class TestController {
  @Post("/post")
  public String post(@Body TestValue testValue) {
    return testValue.toString();
  }

}
