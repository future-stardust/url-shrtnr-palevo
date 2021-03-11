package edu.kpi.testcourse.entities;

import java.util.Objects;

/**
 * A single shortened URL.
 */
public final class UrlAlias {

  private final String alias;
  private final String destinationUrl;

  /**
   * Short URL version (alias).
   *
   * @param alias The unique short identifier of this URL. Prepend <tt>https://example.org/r/</tt>
   *              to get the short URL.
   */
  public UrlAlias(String alias, String destinationUrl) {
    this.alias = alias;
    this.destinationUrl = destinationUrl;
  }

  public String alias() {
    return alias;
  }

  public String destinationUrl() {
    return destinationUrl;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (UrlAlias) obj;
    return Objects.equals(this.alias, that.alias)
      && Objects.equals(this.destinationUrl, that.destinationUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alias, destinationUrl);
  }

  @Override
  public String toString() {
    return "UrlAlias["
      + "alias=" + alias + ", "
      + "destinationUrl=" + destinationUrl + ']';
  }

}
