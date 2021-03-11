package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.AuthToken;

import javax.annotation.Nullable;
import java.util.HashMap;

public class AuthTokenRepositoryFakeImpl implements AuthTokenRepository {
  private final HashMap<String, AuthToken> map = new HashMap<>();

  @Override
  public void createToken(AuthToken authToken) {
    map.put(authToken.token(), authToken);
  }

  @Override
  public @Nullable AuthToken findToken(String token) {
    return map.get(token);
  }

  @Override
  public void deleteToken(String token) {
    map.remove(token);
  }
}
