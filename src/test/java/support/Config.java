package support;

import java.util.Map;

public final class Config {
  private Config() {}

  private static final String DEFAULT_BASE_URL = "https://fakerestapi.azurewebsites.net";
  private static final String DEFAULT_ACCEPT   = "text/plain; v=1.0";

  /**
   * Returns the default base URL used for API requests.
   *
   * <p>This implementation is constant and does not read system properties or
   * environment variables.</p>
   *
   * @return the fixed base URL {@code "https://fakerestapi.azurewebsites.net"}
   */
  public static String baseUrl() {
    return DEFAULT_BASE_URL;
  }

  /**
   * Provides the default HTTP headers to include with API requests.
   *
   * <p>This implementation returns an immutable map containing a single
   * {@code accept} header set to {@code "text/plain; v=1.0"}.</p>
   *
   * @return an immutable map of default header names to values
   */
  public static Map<String, String> defaultHeaders() {
    return Map.of("accept", DEFAULT_ACCEPT);
  }
}
