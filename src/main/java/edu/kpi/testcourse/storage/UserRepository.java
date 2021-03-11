package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.User;

/**
 * Stores user profiles, necessary for signing in.
 */
public interface UserRepository {
  void createUser(User user);
  User findUser(String email);
}
