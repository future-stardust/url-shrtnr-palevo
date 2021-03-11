package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.UrlAlias;

public class UrlRepositoryFakeImpl implements UrlRepository {
  @Override
  public void createUrlAlias(UrlAlias urlAlias) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void findUrlAlias(String alias) {
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
