package edu.kpi.testcourse.logic;

import edu.kpi.testcourse.entities.User;
import edu.kpi.testcourse.storage.UrlRepositoryFakeImpl;
import edu.kpi.testcourse.storage.UserRepositoryFakeImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

  private UserRepositoryFakeImpl users;

  @BeforeEach
  void setUp() {
    users = new UserRepositoryFakeImpl();
  }

  @Test
  void shouldSuccessfullyCreateANewUser() throws Logic.UserIsAlreadyCreated {
    Logic logic = new Logic(users, new UrlRepositoryFakeImpl());

    logic.createNewUser("aaa@bbb.com", "password");

    assertThat(users.findUser("aaa@bbb.com")).isNotNull();
  }

  @Test
  void shouldNotAllowUserCreationIfEmailIsUsed() {
    users.createUser(new User("aaa@bbb.com", "hash"));
    Logic logic = new Logic(users, new UrlRepositoryFakeImpl());

    assertThatThrownBy(() -> {
      logic.createNewUser("aaa@bbb.com", "password");
    }).isInstanceOf(Logic.UserIsAlreadyCreated.class);
  }
}
