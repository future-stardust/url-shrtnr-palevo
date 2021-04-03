package edu.kpi.testcourse.storage;

import com.google.gson.reflect.TypeToken;
import edu.kpi.testcourse.entities.UrlAlias;
import edu.kpi.testcourse.logic.UrlShortenerConfig;
import edu.kpi.testcourse.serialization.JsonTool;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * A file-backed implementation of {@link UrlRepository} suitable for use in production.
 */
public class UrlRepositoryFileImpl implements UrlRepository {

  private final Map<String, UrlAlias> aliases;
  private final JsonTool jsonTool;
  private final UrlShortenerConfig appConfig;


  /**
   * Creates an instance.
   */
  @Inject
  public UrlRepositoryFileImpl(JsonTool jsonTool, UrlShortenerConfig appConfig) {
    this.jsonTool = jsonTool;
    this.appConfig = appConfig;
    this.aliases =
      readUrlsFromJsonDatabaseFile(jsonTool, makeJsonFilePath(appConfig.storageRoot()));
  }

  @Override
  public void createUrlAlias(UrlAlias urlAlias) throws AliasAlreadyExist {
    if (aliases.containsKey(urlAlias.alias())) {
      throw new UrlRepository.AliasAlreadyExist();
    }

    aliases.put(urlAlias.alias(), urlAlias);
    writeUrlsToJsonDatabaseFile(jsonTool, aliases, makeJsonFilePath(appConfig.storageRoot()));

  }

  @Nullable
  @Override
  public UrlAlias findUrlAlias(String alias) {
    return aliases.get(alias);
  }

  @Override
  public void deleteUrlAlias(String email, String alias) throws PermissionDenied {
    for (UrlAlias currentAlias :
      getAllAliasesForUser(email)) {
      if (currentAlias.alias().equals(alias)) {
        aliases.remove(alias);
        writeUrlsToJsonDatabaseFile(jsonTool, aliases, makeJsonFilePath(appConfig.storageRoot()));
        return;
      }
    }
    throw new RuntimeException("Alias " + alias + " was not found among created by the user");
  }

  @Override
  public List<UrlAlias> getAllAliasesForUser(String userEmail) {
    return aliases.values()
      .stream()
      .filter(urlAlias -> urlAlias.email().equals(userEmail))
      .collect(Collectors.toList());
  }

  private static Path makeJsonFilePath(Path storageRoot) {
    return storageRoot.resolve("alias-repository.json");
  }

  private static Map<String, UrlAlias> readUrlsFromJsonDatabaseFile(
    JsonTool jsonTool, Path sourceFilePath
  ) {
    String json;
    try {
      json = Files.readString(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Type type = new TypeToken<HashMap<String, UrlAlias>>() {
    }.getType();
    Map<String, UrlAlias> result = jsonTool.fromJson(json, type);
    if (result == null) {
      throw new RuntimeException("Could not deserialize the alias repository");
    }
    return result;
  }

  private static void writeUrlsToJsonDatabaseFile(
    JsonTool jsonTool, Map<String, UrlAlias> aliases, Path destinationFilePath
  ) {
    String json = jsonTool.toJson(aliases);
    try {
      Files.write(destinationFilePath, json.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
