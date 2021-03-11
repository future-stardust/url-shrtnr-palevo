package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.AuthToken;
import edu.kpi.testcourse.entities.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AuthTokenRepositoryFakeImplTest {
  @Test
  void createsOneToken() {
    // GIVEN
    var tokenRepository = new AuthTokenRepositoryFakeImpl();
    var token = "token1";
    var authToken = new AuthToken(token, "user1@example.org");

    // WHEN
    tokenRepository.createToken(authToken);

    // THEN
    assertThat(tokenRepository.findToken(token)).isEqualTo(authToken);
  }

  @Test
  void findsCorrectToken() {
    // GIVEN
    var tokenRepository = new AuthTokenRepositoryFakeImpl();
    var token1 = "token1";
    var authToken1 = new AuthToken(token1, "user1@example.org");
    var token2 = "token2";
    var authToken2 = new AuthToken(token2, "user1@example.org");
    var token3 = "token3";
    var authToken3 = new AuthToken(token3, "user2@example.org");

    // WHEN
    tokenRepository.createToken(authToken1);
    tokenRepository.createToken(authToken2);
    tokenRepository.createToken(authToken3);

    // THEN
    assertThat(tokenRepository.findToken(token1)).isEqualTo(authToken1);
    assertThat(tokenRepository.findToken(token2)).isEqualTo(authToken2);
    assertThat(tokenRepository.findToken(token3)).isEqualTo(authToken3);
  }

  @Test
  void doesNotFindNonexistentToken() {
    // GIVEN + WHEN
    var tokenRepository = new AuthTokenRepositoryFakeImpl();

    // THEN
    assertThat(tokenRepository.findToken("token1")).isEqualTo(null);
  }

  @Test
  void deletesExistingToken() {
    // GIVEN
    var tokenRepository = new AuthTokenRepositoryFakeImpl();
    var token = "token1";
    var authToken = new AuthToken(token, "user1@example.org");
    tokenRepository.createToken(authToken);
    assertThat(tokenRepository.findToken(token)).isEqualTo(authToken);

    // WHEN
    tokenRepository.deleteToken(token);

    // THEN
    assertThat(tokenRepository.findToken(token)).isEqualTo(null);
  }

}
