package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.UrlAlias;

/**
 * Stores shortened URLs.
 */
public interface UrlRepository {
  void createUrlAlias(UrlAlias urlAlias);
  void findUrlAlias(String alias);
  void deleteUrlAlias(String alias);
  UrlAlias[] getAllAliasesForUser(String userEmail);
}
