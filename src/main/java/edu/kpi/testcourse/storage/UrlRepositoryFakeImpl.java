package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.UrlAlias;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

/**
 * An in-memory fake implementation of {@link UrlRepository}.
 */
public class UrlRepositoryFakeImpl implements UrlRepository {

  //Make it public to make my testing process less painful
  public final HashMap<String, UrlAlias> aliases = new HashMap<>();

  @Override
  public void createUrlAlias(UrlAlias urlAlias) {
    if (aliases.containsKey(urlAlias.alias())) {
      throw new UrlRepository.AliasAlreadyExist();
    }

    aliases.put(urlAlias.alias(), urlAlias);
  }

  @Override
  public @Nullable UrlAlias findUrlAlias(String alias) {
    return aliases.get(alias);
  }

  /**
   * Delete the UrlAlias if exists from aliases HashMap
   *
   * @param email of the user to whom the UrlAlias belongs
   *
   * @param alias the UrlAlias which should be removed
   *
   * @throws IllegalArgumentException if no such email or alias
   */

  @Override
  public void deleteUrlAlias(String email, String alias) {
    UrlAlias foundUrlAlias = null;

    for (UrlAlias urlAlias: aliases.values()) {
      if (foundUrlAlias == null){
        if (urlAlias.email().equals(email) && urlAlias.alias().equals(alias)) {
          foundUrlAlias = urlAlias;
          break;
        };
      }
    }
    if (foundUrlAlias == null) {
      throw new IllegalArgumentException();
    }
    if (aliases.containsKey(alias)){
      aliases.remove(alias);
    }
  }

  @Override
  public List<UrlAlias> getAllAliasesForUser(String userEmail) {
    // TODO: We should implement it
    throw new UnsupportedOperationException();
  }
}
