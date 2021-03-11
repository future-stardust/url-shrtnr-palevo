package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.UrlAlias;

import javax.annotation.Nullable;

public class UrlRepositoryFakeImpl implements UrlRepository {
  @Override
  public void createUrlAlias(UrlAlias urlAlias) {
    throw new UnsupportedOperationException();
  }

  @Override
  public @Nullable UrlAlias findUrlAlias(String alias) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteUrlAlias(String alias) {
    throw new UnsupportedOperationException();
  }

  @Override
  public UrlAlias[] getAllAliasesForUser(String userEmail) {
    throw new UnsupportedOperationException();
  }
}
