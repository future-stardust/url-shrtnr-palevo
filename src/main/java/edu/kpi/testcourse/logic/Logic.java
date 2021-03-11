package edu.kpi.testcourse.logic;

import edu.kpi.testcourse.storage.UrlRepository;
import edu.kpi.testcourse.storage.UserRepository;
import javax.inject.Inject;

/**
 * Business logic of the URL shortener application.
 */
public class Logic {
  private final UserRepository users;
  private final UrlRepository urls;

  /**
   * Creates an instance.
   */
  @Inject
  public Logic(UserRepository users, UrlRepository urls) {
    this.users = users;
    this.urls = urls;
  }

  public String hello() {
    return "{\"first\": \"Hello\", \"second\": \"world!\"}";
  }

}
