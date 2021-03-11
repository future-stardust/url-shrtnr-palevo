package edu.kpi.testcourse.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kpi.testcourse.logic.Logic;
import edu.kpi.testcourse.rest.models.ErrorResponse;
import edu.kpi.testcourse.rest.models.UserSignupRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import javax.inject.Inject;

/**
 * API controller for all REST API endpoints accessible without authentication.
 */
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class PublicApiController {

  private final Logic logic;
  private final ObjectMapper objectMapper;

  @Inject
  public PublicApiController(Logic logic, ObjectMapper objectMapper) {
    this.logic = logic;
    this.objectMapper = objectMapper;
  }

  /**
   * Sign-up (user creation) request.
   *
   * @param request request with email and password
   * @return nothing or error description
   */
  @Post(value = "/users/signup", produces = MediaType.APPLICATION_JSON)
  public HttpResponse<String> signup(UserSignupRequest request) throws JsonProcessingException {
    try {
      logic.createNewUser(request.email(), request.password());
      return HttpResponse.status(HttpStatus.CREATED);
    } catch (Logic.UserIsAlreadyCreated e) {
      return HttpResponse.serverError(
        objectMapper.writeValueAsString(new ErrorResponse(0, e.getMessage())));
    }
  }
}
