package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.AuthToken;

public class AuthTokenRepositoryFakeImpl implements AuthTokenRepository {
  @Override
  public void createToken(AuthToken authToken) {
    throw new UnsupportedOperationException();
  }

  @Override
  public AuthToken findToken(String token) {
    throw new UnsupportedOperationException();
  }
}
