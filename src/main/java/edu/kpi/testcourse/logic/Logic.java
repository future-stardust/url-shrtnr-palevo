package edu.kpi.testcourse.logic;

import edu.kpi.testcourse.storage.AuthTokenRepository;
import edu.kpi.testcourse.storage.UrlRepository;
import edu.kpi.testcourse.storage.UserRepository;
import javax.inject.Inject;

/**
 * Business logic of the URL shortener application.
 */
public class Logic {
  private final UserRepository users;
  private final AuthTokenRepository tokens;
  private final UrlRepository urls;

  /**
   * Creates an instance.
   */
  @Inject
  public Logic(UserRepository users, AuthTokenRepository tokens, UrlRepository urls) {
    this.users = users;
    this.tokens = tokens;
    this.urls = urls;
  }

  public String hello() {
    return "{\"first\": \"Hello\", \"second\": \"world!\"}";
  }

}
