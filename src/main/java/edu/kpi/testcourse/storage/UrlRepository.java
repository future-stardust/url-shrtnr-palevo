package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.UrlAlias;
import javax.annotation.Nullable;

/**
 * Stores shortened URLs.
 */
public interface UrlRepository {
  /**
   * Stores the given URL alias in the repository if it does not already exist.
   *
   * @throws RuntimeException if the repository already contains a URL alias with this short name.
   */
  void createUrlAlias(UrlAlias urlAlias);

  /**
   * Returns complete information about the URL alias with the given short name.
   */
  @Nullable UrlAlias findUrlAlias(String alias);

  /**
   * Deletes the URL alias with the given short name.
   *
   * @throws RuntimeException if the repository does not contain a URL alias with this short name.
   */
  void deleteUrlAlias(String alias);

  /**
   * Finds all URLs that belong to the user with the given email.
   */
  UrlAlias[] getAllAliasesForUser(String userEmail);
}
