package edu.kpi.testcourse.rest;

import edu.kpi.testcourse.Main;
import edu.kpi.testcourse.logic.Logic;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;

/**
 * REST API controller that provides logic for Micronaut framework.
 */
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class AuthenticatedApiController {

  private final Logic logic;

  @Inject
  public AuthenticatedApiController(Logic logic) {
    this.logic = logic;
  }

}
