package edu.kpi.testcourse.logic;

import edu.kpi.testcourse.entities.UrlAlias;
import edu.kpi.testcourse.entities.User;
import edu.kpi.testcourse.storage.UrlRepository;
import edu.kpi.testcourse.storage.UrlRepository.AliasAlreadyExist;
import edu.kpi.testcourse.storage.UserRepository;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Business logic of the URL shortener application.
 */
public class Logic {
  private final UserRepository users;
  private final UrlRepository urls;
  private final HashUtils hashUtils;

  /**
   * Creates an instance.
   */
  public Logic(UserRepository users, UrlRepository urls) {
    this.users = users;
    this.urls = urls;
    this.hashUtils = new HashUtils();
  }

  /**
   * Creates empty instance.
   */
  public Logic() {
    this.users = new UserRepository() {
      @Override
      public void createUser(User user) {

      }

      @Nullable
      @Override
      public User findUser(String email) {
        return null;
      }
    };
    this.urls = new UrlRepository() {
      @Override
      public void createUrlAlias(UrlAlias urlAlias) throws AliasAlreadyExist {

      }

      @Nullable
      @Override
      public UrlAlias findUrlAlias(String alias) {
        return null;
      }

      @Override
      public void deleteUrlAlias(String email, String alias) throws PermissionDenied {

      }

      @Override
      public List<UrlAlias> getAllAliasesForUser(String userEmail) {
        return null;
      }
    };
    this.hashUtils = new HashUtils();
  }

  /**
   * Create a new user.
   *
   * @param email users email
   * @param password users password
   * @throws UserIsAlreadyCreated is thrown if user is already created
   */
  public void createNewUser(String email, String password) throws UserIsAlreadyCreated {
    if (users.findUser(email) != null) {
      throw new UserIsAlreadyCreated();
    } else {
      users.createUser(new User(email, hashUtils.generateHash(password)));
    }
  }

  /**
   * Gives an answer if user is registered and password is correct.
   *
   * @param email a users email
   * @param password a users password
   * @return if user is registered and password is correct
   */
  public boolean isUserValid(String email, String password) {
    User user = users.findUser(email);
    if (user == null) {
      return false;
    }

    return hashUtils.validatePassword(password, user.passwordHash());
  }

  /**
   * Create a new URL alias (shortened version).
   *
   * @param email an email of a user that creates the alias
   * @param url a full URL
   * @param alias a proposed alias
   *
   * @return a shortened URL
   */
  public String createNewAlias(String email, String url, String alias) throws AliasAlreadyExist {
    String finalAlias;
    if (alias == null || alias.isEmpty()) {
      // TODO: Generate short alias
      throw new UnsupportedOperationException("Is not implemented yet");
    } else {
      finalAlias = alias;
    }

    urls.createUrlAlias(new UrlAlias(finalAlias, url, email));

    return finalAlias;
  }

  /**
   * Get full URL by alias.
   *
   * @param alias a short URL alias
   * @return a full URL
   */
  public String findFullUrl(String alias) {
    UrlAlias urlAlias = urls.findUrlAlias(alias);

    if (urlAlias != null) {
      return urlAlias.destinationUrl();
    }

    return null;
  }

  /**
   * Return text + random symbols.
   *
   * @param text witch must be changed
   * @return chanded text
   */
    public String randomSymbols(String text){
      char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
      Random random = new Random();
      for (int i = 0; i < 5; i++) {
        text += chars[random.nextInt(chars.length)];
      }
      return text;
    }

  /**
   * Generate random email.
   */
    public String generateEmail(){
      String tempEmail = "";
      tempEmail = randomSymbols(tempEmail);
      tempEmail += "@";
      tempEmail = randomSymbols(tempEmail);
      tempEmail += ".com";
      return tempEmail;
    }

  /**
   * Generate random url.
   */
    public String generateUrl(){
      String tempStr = "http://";
      tempStr = randomSymbols(tempStr);
      tempStr += ".com/longUrl";
      return tempStr;
    }

  /**
   * Generate random short url.
   */
    public String generateShortUrl(String shortUrl){
      String tempStr = shortUrl;
      tempStr = randomSymbols(tempStr);
      return tempStr;
    }

  /**
   * Generate Alias witch path with baseurl -> http:localhost:8080/urls/generate_alias
   */
  public void generateAlias(Logic logic, String pathShortUrl) {
      String email = generateEmail();
      String url = generateUrl();
      String alias = generateShortUrl(pathShortUrl);

      logic.createNewAlias(email, url, alias);
    }

    /**
   * Error for situation when we are trying to register already registered user.
   */
  public static class UserIsAlreadyCreated extends Throwable {
    public UserIsAlreadyCreated() {
      super("User with such email is already created");
    }
  }

}
