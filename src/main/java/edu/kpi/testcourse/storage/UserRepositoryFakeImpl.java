package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.User;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryFakeImpl implements UserRepository {
  private final Map<String, User> users = new HashMap<>();

  @Override
  public void createUser(User user) {
    if (users.putIfAbsent(user.email(), user) != null) {
      throw new RuntimeException("User already exists");
    }
  }

  @Override
  public @Nullable User findUser(String email) {
    return users.get(email);
  }
}
