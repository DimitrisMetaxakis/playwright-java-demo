package support;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import java.util.Objects;

public final class ApiFactory {
  private final Playwright playwright;
  private final APIRequestContext request;

  private ApiFactory(Playwright pw, APIRequestContext ctx) {
    this.playwright = Objects.requireNonNull(pw);
    this.request = Objects.requireNonNull(ctx);
  }

  /**
   * Creates a configured API factory with a shared {@link com.microsoft.playwright.APIRequestContext}.
   *
   * <p>Initializes a new {@link com.microsoft.playwright.Playwright} instance and builds a request context
   * using {@link support.Config#baseUrl()} and {@link support.Config#defaultHeaders()}.</p>
   *
   * @return a ready-to-use {@code ApiFactory} wrapping Playwright and its {@code APIRequestContext}
   */
  public static ApiFactory create() {

    Playwright pw = Playwright.create();

    APIRequestContext ctx = pw.request().newContext(
        new APIRequest.NewContextOptions()
            .setBaseURL(Config.baseUrl())
            .setExtraHTTPHeaders(Config.defaultHeaders())
    );
    return new ApiFactory(pw, ctx);
  }

  /**
   * Returns the underlying {@link com.microsoft.playwright.APIRequestContext} for ad-hoc HTTP calls
   * or advanced assertions that are not covered by typed clients.
   *
   * @return the shared request context
   */

  public APIRequestContext request() {
    return request;
  }

  /**
   * Provides a typed client for the Authors resource.
   *
   * <p>The client encapsulates endpoint paths, headers, and response parsing so that tests can use
   * intention-revealing methods (e.g., {@code getAuthors()}) instead of low-level HTTP calls.</p>
   *
   * @return an {@link support.AuthorsClient} bound to this factory's request context
   */

  public AuthorsClient authorsClient() {
    return new AuthorsClient(request);
  }

  /**
   * Closes the factory and releases Playwright resources.
   *
   * <p>Disposes the {@link com.microsoft.playwright.APIRequestContext} and then closes the
   * {@link com.microsoft.playwright.Playwright} instance. Call from test teardown (e.g., {@code @AfterAll})
   * or a JUnit 5 extension's lifecycle hook.</p>
   */
  public void close() {
    try { request.dispose(); } finally { playwright.close(); }
  }
}

