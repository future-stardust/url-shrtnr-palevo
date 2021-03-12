package edu.kpi.testcourse.serialization;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonToolJacksonImplTest {

  private JsonToolJacksonImpl json;

  @BeforeEach
  void setUp() {
    json = new JsonToolJacksonImpl();
  }

  @Test
  void shouldSerializeDesirializeMap() {
    // GIVEN
    var map = new HashMap<String, String>();
    map.put("a", "b");
    map.put("c", "d");

    // WHEN
    var s = json.toJson(map);
    var result = json.fromJson(s, new TypeReference<HashMap<String, String>>() {}.getType());

    // THEN
    assertThat(result).isEqualTo(map);
  }
}
