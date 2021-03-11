package edu.kpi.testcourse.logic;

import edu.kpi.testcourse.entities.User;
import edu.kpi.testcourse.storage.UrlRepositoryFakeImpl;
import edu.kpi.testcourse.storage.UserRepositoryFakeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LogicTest {

  private UserRepositoryFakeImpl users;

  @BeforeEach
  void setUp() {
    users = new UserRepositoryFakeImpl();
  }

  @Test
  void shouldSuccessfullyCreateANewUser() throws Logic.UserIsAlreadyCreated {
    // GIVEN
    Logic logic = new Logic(users, new UrlRepositoryFakeImpl());

    // WHEN
    logic.createNewUser("aaa@bbb.com", "password");

    // THEN
    assertThat(users.findUser("aaa@bbb.com")).isNotNull();
  }

  @Test
  void shouldNotAllowUserCreationIfEmailIsUsed() {
    // GIVEN
    users.createUser(new User("aaa@bbb.com", "hash"));
    Logic logic = new Logic(users, new UrlRepositoryFakeImpl());

    assertThatThrownBy(() -> {
      // WHEN
      logic.createNewUser("aaa@bbb.com", "password");
    })
      // THEN
      .isInstanceOf(Logic.UserIsAlreadyCreated.class);
  }

  @Test
  void shouldAuthorizeUser() throws Logic.UserIsAlreadyCreated {
    // GIVEN
    Logic logic = new Logic(users, new UrlRepositoryFakeImpl());

    // WHEN
    logic.createNewUser("aaa@bbb.com", "password");

    // THEN
    assertThat(logic.isUserValid("aaa@bbb.com", "password")).isTrue();
  }

}
