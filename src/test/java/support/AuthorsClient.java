package support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class AuthorsClient {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private final APIRequestContext request;

  /**
   * Creates a client for the Authors API using the provided Playwright request context.
   *
   * @param request the shared {@link com.microsoft.playwright.APIRequestContext} to issue HTTP calls with (must be non-null)
   */
  public AuthorsClient(APIRequestContext request) {
    this.request = requireNonNull(request);
  }

  /**
   * Performs a raw GET request to {@code /api/v1/Authors}.
   *
   * <p>No status-code validation or body parsing is performed here; callers can inspect the
   * returned {@link com.microsoft.playwright.APIResponse} for status, headers, and body.</p>
   *
   * @return the raw {@link com.microsoft.playwright.APIResponse} from the Authors endpoint
   */

  public APIResponse getAuthorsRaw() {
    return request.get("/api/v1/Authors");
  }

  /**
   * Retrieves all authors and parses the JSON response into a list of maps.
   *
   * <p>On success (HTTP 2xx), the response body is parsed using Jackson into
   * {@code List<Map<String, Object>>} where each map represents an author record.</p>
   *
   * @return a list of authors parsed from the response body
   * @throws IllegalStateException if the HTTP response is non-2xx or if JSON parsing fails
   */

  public List<Map<String, Object>> getAuthors() {
    APIResponse res = getAuthorsRaw();
    if (!res.ok()) {
      throw new IllegalStateException("GET /Authors failed: " + res.status() + " " + res.statusText());
    }
    try {
      String body = res.text();
      return MAPPER.readValue(body, new TypeReference<List<Map<String, Object>>>() {});
    } catch (Exception e) {
      throw new IllegalStateException("Failed to parse authors JSON", e);
    }
  }
}
