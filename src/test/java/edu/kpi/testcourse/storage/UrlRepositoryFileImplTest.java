package edu.kpi.testcourse.storage;

import edu.kpi.testcourse.entities.UrlAlias;
import edu.kpi.testcourse.logic.UrlShortenerConfig;
import edu.kpi.testcourse.serialization.JsonToolJacksonImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UrlRepositoryFileImplTest {
  UrlShortenerConfig appConfig;
  UrlRepository urlRepository;

  @BeforeEach
  void setUp() {
    try {
      appConfig = new UrlShortenerConfig(
        Files.createTempDirectory("alias-repository-file-test"));
      Files.write(appConfig.storageRoot().resolve("alias-repository.json"), "{}".getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    urlRepository = new UrlRepositoryFileImpl(new JsonToolJacksonImpl(), appConfig);
  }

  @AfterEach
  void tearDown() {
    try {
      Files.delete(appConfig.storageRoot().resolve("alias-repository.json"));
      Files.delete(appConfig.storageRoot());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Test
  void shouldCreateAlias() {
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";
    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);

    urlRepository.createUrlAlias(testUrlAlias);

    assertThat(urlRepository.findUrlAlias(testAlias)).isEqualTo(testUrlAlias);
  }

  @Test
  void shouldSerializesOneAlias() throws IOException {
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";
    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);

    urlRepository.createUrlAlias(testUrlAlias);

    Assertions.assertThat(
      Files.readString(appConfig.storageRoot().resolve("alias-repository.json"), StandardCharsets.UTF_8))
      .isEqualTo("{\"testAlias\":{\"alias\":\"testAlias\",\"destinationUrl\":\"testDestinationUrl\",\"email\":\"user@test.com\"}}");
  }

  @Test
  void shouldDeserializesOneAlias() {
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";
    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);
    urlRepository.createUrlAlias(testUrlAlias);

    urlRepository = new UrlRepositoryFileImpl(new JsonToolJacksonImpl(), appConfig);

    Assertions.assertThat(urlRepository.findUrlAlias(testAlias)).isEqualTo(testUrlAlias);
  }

  @Test
  void shouldNotCreateDuplicateAlias() {
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";
    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);

    urlRepository.createUrlAlias(testUrlAlias);

    assertThrows(UrlRepository.AliasAlreadyExist.class, () -> urlRepository.createUrlAlias(testUrlAlias));
  }

  @Test
  void shouldFindCorrectAlias() {
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";
    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);

    var testAlias1 = "testAlias1";
    var testDestinationUrl1 = "testDestinationUrl1";
    var testEmail1 = "user1@test.com";
    UrlAlias testUrlAlias1 = new UrlAlias(testAlias1, testDestinationUrl1, testEmail1);

    urlRepository.createUrlAlias(testUrlAlias);
    urlRepository.createUrlAlias(testUrlAlias1);

    Assertions.assertThat(urlRepository.findUrlAlias(testAlias)).isEqualTo(testUrlAlias);
    Assertions.assertThat(urlRepository.findUrlAlias(testAlias1)).isEqualTo(testUrlAlias1);
  }

  @Test
  void shouldReturnNullWhenAliasNotFound() {
    Assertions.assertThat(urlRepository.findUrlAlias("anyString")).isEqualTo(null);
  }

  @Test
  void shouldReturnListWithOneAlias() {
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";
    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);

    urlRepository.createUrlAlias(testUrlAlias);

    assertEquals(1, urlRepository.getAllAliasesForUser(testEmail).size());
    assertEquals(testUrlAlias, urlRepository.findUrlAlias(testAlias));
  }

  @Test
  void shouldDeleteOneAlias() {
    var testAlias = "testAlias";
    var testDestinationUrl = "testDestinationUrl";
    var testEmail = "user@test.com";

    UrlAlias testUrlAlias = new UrlAlias(testAlias, testDestinationUrl, testEmail);
    urlRepository.createUrlAlias(testUrlAlias);
    urlRepository.deleteUrlAlias(testEmail, testAlias);

    assertNull(urlRepository.findUrlAlias(testAlias));
  }

  @Test
  void shouldThrowAliasNotExistExceptionWhenDeleteAndGivenAliasNotExist() {
    var testAlias = "testAlias";
    var testEmail = "user@test.com";

    assertThrows(UrlRepository.AliasNotExist.class, () -> urlRepository.deleteUrlAlias(testEmail, testAlias));
  }
}
