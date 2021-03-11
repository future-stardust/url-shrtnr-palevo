package edu.kpi.testcourse.rest;

import edu.kpi.testcourse.Main;
import edu.kpi.testcourse.logic.Logic;
import edu.kpi.testcourse.storage.AuthTokenRepository;
import edu.kpi.testcourse.storage.UserRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;

/**
 * TODO
 */
@Controller
public class PublicApiController {

  private final Logic logic;

  @Inject
  public PublicApiController(Logic logic) {
    this.logic = logic;
  }

  @Secured(SecurityRule.IS_ANONYMOUS)
  @Get(value = "/hello", produces = MediaType.APPLICATION_JSON)
  public String hello() {
    return logic.hello();
  }

}
