package edu.kpi.testcourse;

import edu.kpi.testcourse.storage.*;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class BeanFactory {

  @Singleton
  AuthTokenRepository createAuthTokenRepository() {
    return new AuthTokenRepositoryFakeImpl();
  }

  @Singleton
  UrlRepository createUrlRepository() {
    return new UrlRepositoryFakeImpl();
  }

  @Singleton
  UserRepository createUserRepository() {
    return new UserRepositoryFakeImpl();
  }
}
