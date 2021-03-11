package edu.kpi.testcourse.entities;

import java.util.Objects;

/**
 * A bearer token of an authenticated user.
 */
public final class AuthToken {

  private final String token;
  private final String userEmail;

  public AuthToken(String token, String userEmail) {
    this.token = token;
    this.userEmail = userEmail;
  }

  public String token() {
    return token;
  }

  public String userEmail() {
    return userEmail;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (AuthToken) obj;
    return Objects.equals(this.token, that.token)
      && Objects.equals(this.userEmail, that.userEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, userEmail);
  }

  @Override
  public String toString() {
    return "AuthToken["
      + "token=" + token + ", "
      + "userEmail=" + userEmail + ']';
  }

}
