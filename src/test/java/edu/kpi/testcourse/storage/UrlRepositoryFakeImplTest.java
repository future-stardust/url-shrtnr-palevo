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

  @Test
  void shouldDeleteOneAlias() {
    UrlRepository urlRepository = new UrlRepositoryFakeImpl();
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";

    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);
    urlRepository.createUrlAlias(testUrlAlias);
    urlRepository.deleteUrlAlias(testEmail, testAlias);

    System.out.println(urlRepository.findUrlAlias(testAlias));
    assertNull(urlRepository.findUrlAlias(testAlias));
  }

  @Test
  void shouldThrowAliasNotExistExceptionWhenDeleteAliasThatNotExist() {
    UrlRepository urlRepository = new UrlRepositoryFakeImpl();
    var testAlias = "testAlias";
    var testEmail = "user@test.com";

    assertThrows(UrlRepository.AliasNotExist.class, () -> urlRepository.deleteUrlAlias(testEmail, testAlias));
  }
}
