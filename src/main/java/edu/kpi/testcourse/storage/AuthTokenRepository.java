package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.AuthToken;

/**
 * Stores authentication tokens.
 */
public interface AuthTokenRepository {
  void createToken(AuthToken authToken);
  AuthToken findToken(String token);
}
