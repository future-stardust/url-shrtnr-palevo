package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.UrlAlias;

import javax.annotation.Nullable;

/**
 * Stores shortened URLs.
 */
public interface UrlRepository {
  void createUrlAlias(UrlAlias urlAlias);
  @Nullable UrlAlias findUrlAlias(String alias);
  void deleteUrlAlias(String alias);
  UrlAlias[] getAllAliasesForUser(String userEmail);
}
