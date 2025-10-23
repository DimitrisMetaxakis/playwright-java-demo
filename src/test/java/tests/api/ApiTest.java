package tests.api;

import org.junit.jupiter.api.Test;
import tests.base.ApiBaseTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.Conversions.asInt;

class ApiTest extends ApiBaseTest {

  @Test
  void getAuthors_containsExpectedAuthor1() {
    List<Map<String, Object>> data = authors.getAuthors();
    assertFalse(data.isEmpty(), "Authors list should not be empty");

    Map<String, Object> a1 = data.stream()
            .filter(m -> asInt(m.get("id")) == 1)
            .findFirst()
            .orElseThrow(() -> new AssertionError("Author with id=1 not found"));

    assertAll(
            () -> assertEquals(1, asInt(a1.get("id")), "id mismatch"),
            () -> assertEquals(1, asInt(a1.get("idBook")), "idBook mismatch"),
            () -> assertEquals("First Name 1", String.valueOf(a1.get("firstName")), "firstName mismatch"),
            () -> assertEquals("Last Name 1", String.valueOf(a1.get("lastName")), "lastName mismatch")
    );
  }


}
