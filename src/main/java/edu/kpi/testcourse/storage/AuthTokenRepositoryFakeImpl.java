package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.AuthToken;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/**
 * An in-memory fake implementation of {@link AuthTokenRepository}.
 */
public class AuthTokenRepositoryFakeImpl implements AuthTokenRepository {
  private final Map<String, AuthToken> map = new HashMap<>();

  @Override
  public void createToken(AuthToken authToken) {
    if (map.putIfAbsent(authToken.token(), authToken) != null) {
      throw new RuntimeException("Token already exists");
    }
  }

  @Override
  public @Nullable AuthToken findToken(String token) {
    return map.get(token);
  }

  @Override
  public void deleteToken(String token) {
    if (map.remove(token) == null) {
      throw new RuntimeException("Token does not exist");
    }
  }
}
