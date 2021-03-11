package edu.kpi.testcourse.logic;

import edu.kpi.testcourse.storage.AuthTokenRepository;
import edu.kpi.testcourse.storage.UrlRepository;
import edu.kpi.testcourse.storage.UserRepository;

import javax.inject.Inject;

public class LogicImpl implements Logic {

  private final UserRepository users;
  private final AuthTokenRepository tokens;
  private final UrlRepository urls;

  @Inject
  public LogicImpl(UserRepository users, AuthTokenRepository tokens, UrlRepository urls) {
    this.users = users;
    this.tokens = tokens;
    this.urls = urls;
  }

  @Override
  public String hello() {
    return "{\"first\": \"Hello\", \"second\": \"world!\"}";
  }
}
