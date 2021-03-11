package edu.kpi.testcourse.logic;

import edu.kpi.testcourse.entities.User;
import edu.kpi.testcourse.storage.UrlRepository;
import edu.kpi.testcourse.storage.UserRepository;
import javax.inject.Inject;

/**
 * Business logic of the URL shortener application.
 */
public class Logic {
  private final UserRepository users;
  private final UrlRepository urls;

  /**
   * Creates an instance.
   */
  @Inject
  public Logic(UserRepository users, UrlRepository urls) {
    this.users = users;
    this.urls = urls;
  }

  public String hello() {
    return "{\"first\": \"Hello\", \"second\": \"world!\"}";
  }

  public void createNewUser(String email, String password) throws UserIsAlreadyCreated {
    if (users.findUser(email) != null) {
      throw new UserIsAlreadyCreated();
    } else {
      users.createUser(new User(email, password));
    }
  }

  public static class UserIsAlreadyCreated extends Throwable {
    public UserIsAlreadyCreated() {
      super("User with such email is already created");
    }
  }

}
