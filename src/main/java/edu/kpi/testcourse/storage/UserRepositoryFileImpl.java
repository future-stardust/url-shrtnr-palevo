package edu.kpi.testcourse.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.kpi.testcourse.entities.User;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * A file-backed implementation of {@link UserRepository} suitable for use in production.
 */
public class UserRepositoryFileImpl implements UserRepository {
  // User profiles, keyed by email.
  private final Map<String, User> users;

  private final Gson gson;

  // Full path to the DB root directory.
  private final String storageRoot;

  /**
   * Creates an instance for production.
   */
  @Inject
  public UserRepositoryFileImpl(Gson gson) {
    this.gson = gson;
    this.storageRoot = "/tmp/url-shortener-db";
    this.users = readFromFile();
  }

  /**
   * Creates an instance for use in unit tests.
   */
  UserRepositoryFileImpl(Gson gson, String storageRoot) {
    this.gson = gson;
    this.storageRoot = storageRoot;
    this.users = readFromFile();
  }

  @Override
  public void createUser(User user) {
    if (users.putIfAbsent(user.email(), user) != null) {
      throw new RuntimeException("User already exists");
    }
    writeToFile();
  }

  @Override
  public @Nullable User findUser(String email) {
    return users.get(email);
  }

  private Path getJsonFilePath() {
    return Paths.get(storageRoot + "/user-repository.json");
  }

  private Map<String, User> readFromFile() {
    String json;
    try {
      json = Files.readString(getJsonFilePath(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    Type type = new TypeToken<HashMap<String, User>>(){}.getType();
    Map<String, User> result = gson.fromJson(json, type);
    if (result == null) {
      throw new RuntimeException("Could not deserialize the user repository");
    }
    return result;
  }

  private void writeToFile() {
    String json = gson.toJson(users);
    try {
      Files.write(getJsonFilePath(), json.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
