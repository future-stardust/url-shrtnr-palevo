package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.AuthToken;

import javax.annotation.Nullable;

/**
 * Stores authentication tokens.
 */
public interface AuthTokenRepository {
  void createToken(AuthToken authToken);
  @Nullable
  AuthToken findToken(String token);
  void deleteToken(String token);
}
