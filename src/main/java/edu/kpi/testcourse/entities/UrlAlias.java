package edu.kpi.testcourse.entities;

import java.util.Objects;

/**
 * A single shortened URL.
 */
public final class UrlAlias {

  private final String alias;
  private final String destinationUrl;
  private final String email;

  /**
   * Short URL version (alias).
   *
   * @param alias The unique short identifier of this URL. Prepend <tt>https://example.org/r/</tt>
   *              to get the short URL.
   * @param destinationUrl the full version of URL
   * @param email an email of a user that created this alias
   */
  public UrlAlias(String alias, String destinationUrl, String email) {
    this.alias = alias;
    this.destinationUrl = destinationUrl;
    this.email = email;
  }

  public String alias() {
    return alias;
  }

  public String destinationUrl() {
    return destinationUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UrlAlias urlAlias = (UrlAlias) o;
    return Objects.equals(alias, urlAlias.alias) && Objects
      .equals(destinationUrl, urlAlias.destinationUrl) && Objects
      .equals(email, urlAlias.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alias, destinationUrl, email);
  }

  @Override
  public String toString() {
    return "UrlAlias{"
      + "alias='" + alias + '\''
      + ", destinationUrl='" + destinationUrl + '\''
      + ", email='" + email + '\''
      + '}';
  }
}
