package edu.kpi.testcourse.rest;

import edu.kpi.testcourse.logic.Logic;
import io.micronaut.http.annotation.Controller;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import javax.inject.Inject;

/**
 * API controller for all REST API endpoints that require authentication.
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
