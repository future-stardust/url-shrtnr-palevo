package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.User;

import javax.annotation.Nullable;

/**
 * Stores user profiles, necessary for signing in.
 */
public interface UserRepository {
  void createUser(User user);
  @Nullable User findUser(String email);
}
