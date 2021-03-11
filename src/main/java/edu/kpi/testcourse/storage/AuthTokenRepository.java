package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.AuthToken;

import javax.annotation.Nullable;

/**
 * Stores authentication tokens.
 */
public interface AuthTokenRepository {
  /**
   * Stores the given token in the repository if it does not already exist.
   * @throws RuntimeException if the token already exists in the repository.
   */
  void createToken(AuthToken authToken);

  /**
   * @returns complete information about the given token or null if it does not exist.
   */
  @Nullable AuthToken findToken(String token);

  /**
   * Removes the given token from the repository.
   * @throws RuntimeException if the token does not exist in the repository.
   */
  void deleteToken(String token);
}
