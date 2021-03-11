package edu.kpi.testcourse.entities;

import java.util.Objects;

/**
 * User profile, sufficient for signing in. User-generated content is stored separately.
 */
public final class User {

  private final String email;
  private final String passwordHash;

  public User(String email, String passwordHash) {
    this.email = email;
    this.passwordHash = passwordHash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (User) obj;
    return Objects.equals(this.email, that.email)
      && Objects.equals(this.passwordHash, that.passwordHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, passwordHash);
  }

  @Override
  public String toString() {
    return "User["
      + "email=" + email + ", "
      + "passwordHash=" + passwordHash + ']';
  }

  public String email() {
    return email;
  }

  public String passwordHash() {
    return passwordHash;
  }


}
