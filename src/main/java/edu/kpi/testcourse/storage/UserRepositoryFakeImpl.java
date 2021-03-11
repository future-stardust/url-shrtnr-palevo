package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryFakeImpl implements UserRepository {
  private final Map<String, User> users = new HashMap<>();

  @Override
  public void createUser(User user) {
    users.put(user.email(), user);
  }

  @Override
  public User findUser(String email) {
    return users.get(email);
  }
}
