package edu.kpi.testcourse;

import edu.kpi.testcourse.storage.AuthTokenRepository;
import edu.kpi.testcourse.storage.AuthTokenRepositoryFakeImpl;
import edu.kpi.testcourse.storage.UrlRepository;
import edu.kpi.testcourse.storage.UrlRepositoryFakeImpl;
import edu.kpi.testcourse.storage.UserRepository;
import edu.kpi.testcourse.storage.UserRepositoryFakeImpl;
import io.micronaut.context.annotation.Factory;
import javax.inject.Singleton;

/**
 * Factory for all injectable classes in our application.
 */
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
