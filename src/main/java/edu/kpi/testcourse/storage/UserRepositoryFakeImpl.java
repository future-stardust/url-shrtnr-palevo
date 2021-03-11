package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.User;

public class UserRepositoryFakeImpl implements UserRepository {
  @Override
  public void createUser(User user) {
    throw new UnsupportedOperationException();
  }

  @Override
  public User findUser(String email) {
    throw new UnsupportedOperationException();
  }
}
