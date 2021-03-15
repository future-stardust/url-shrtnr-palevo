package edu.kpi.testcourse.storage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import edu.kpi.testcourse.entities.UrlAlias;
import org.junit.jupiter.api.Test;

class UrlRepositoryFakeImplTest {

  @Test
  void shouldCreateAlias() {
    //GIVEN
    UrlRepository repo = new UrlRepositoryFakeImpl();

    //WHEN
    UrlAlias alias = new UrlAlias("http://r.com/short", "http://g.com/long", "aaa@bbb.com");
    repo.createUrlAlias(alias);

    //THEN
    assertThat(repo.findUrlAlias("http://r.com/short")).isEqualTo(alias);
  }

  @Test
  void shouldNotAllowToCreateSameAliases() {
    //GIVEN
    UrlRepository repo = new UrlRepositoryFakeImpl();

    //WHEN
    UrlAlias alias1 = new UrlAlias("http://r.com/short", "http://g.com/long1", "aaa@bbb.com");
    repo.createUrlAlias(alias1);

    //THEN
    UrlAlias alias2 = new UrlAlias("http://r.com/short", "http://g.com/long2", "aaa@bbb.com");
    assertThatThrownBy(() -> {
      repo.createUrlAlias(alias2);
    }).isInstanceOf(UrlRepository.AliasAlreadyExist.class);
  }

  /**
   * Checking the main ability of delete function: TO DELETE!
   */
  @Test
  void shouldDeleteChosenAlias() {
    //GIVEN
    UrlRepositoryFakeImpl repo = new UrlRepositoryFakeImpl();

    //WHEN
    UrlAlias alias1 = new UrlAlias("http://r.com/short", "http://g.com/long1", "aaa@bbb.com");
    repo.createUrlAlias(alias1);
    repo.deleteUrlAlias(alias1.email(), alias1.alias());

    //THEN
    assertFalse(repo.aliases.containsValue(alias1));
  }

  /**
   * Test for deleting if alias wasn't added to repo
   */
  @Test
  void shouldCrashOnDelete() {
    //GIVEN
    UrlRepositoryFakeImpl repo = new UrlRepositoryFakeImpl();

    //WHEN
    UrlAlias alias1 = new UrlAlias("http://r.com/short", "http://g.com/long1", "aaa@bbb.com");

    //THEN
    assertThrows(IllegalArgumentException.class, ()->{repo.deleteUrlAlias(alias1.email(), alias1.alias());});
  }



}
