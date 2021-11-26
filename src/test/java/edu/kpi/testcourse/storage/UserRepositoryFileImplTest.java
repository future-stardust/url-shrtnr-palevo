package edu.kpi.testcourse.storage;

import com.google.gson.Gson;
import edu.kpi.testcourse.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRepositoryFileImplTest {
  Path repositoryDirectory;
  UserRepository userRepository;

  @BeforeEach
  void setUp() {
    try {
      repositoryDirectory = Files.createTempDirectory("user-repository-file-test");
      Files.write(repositoryDirectory.resolve("user-repository.json"), "{}".getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    userRepository = new UserRepositoryFileImpl(new Gson(), repositoryDirectory.toString());
  }

  @AfterEach
  void tearDown() {
    try {
      Files.delete(repositoryDirectory.resolve("user-repository.json"));
      Files.delete(repositoryDirectory);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Test
  void createsOneUser() {
    // GIVEN
    var email = "user1@example.org";
    var user = new User(email, "hash1");

    // WHEN
    userRepository.createUser(user);

    // THEN
    assertThat(userRepository.findUser(email)).isEqualTo(user);
  }

  @Test
  void serializesOneUser() throws IOException {
    // GIVEN
    var email = "user1@example.org";
    var user = new User(email, "hash1");

    // WHEN
    userRepository.createUser(user);

    // THEN
    assertThat(
      Files.readString(repositoryDirectory.resolve("user-repository.json"), StandardCharsets.UTF_8))
      .isEqualTo("{\"user1@example.org\":{\"email\":\"user1@example.org\",\"passwordHash\":\"hash1\"}}");
  }

  @Test
  void doesNotCreateDuplicateUser() {
    // GIVEN
    var email = "user1@example.org";
    var user = new User(email, "hash1");
    userRepository.createUser(user);

    // WHEN + THEN
    assertThrows(RuntimeException.class, () -> userRepository.createUser(user));
  }

  @Test
  void findsCorrectUser() {
    // GIVEN
    var email1 = "user1@example.org";
    var user1 = new User(email1, "hash1");
    var email2 = "user2@example.org";
    var user2 = new User(email2, "hash2");

    // WHEN
    userRepository.createUser(user1);
    userRepository.createUser(user2);

    // THEN
    assertThat(userRepository.findUser(email1)).isEqualTo(user1);
    assertThat(userRepository.findUser(email2)).isEqualTo(user2);
  }

  @Test
  void doesNotFindNonexistentUser() {
    // GIVEN + WHEN
    // see setUp().

    // THEN
    assertThat(userRepository.findUser("user1@example.org")).isEqualTo(null);
  }

}
