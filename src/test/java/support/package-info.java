
/**
 * API support package.
 *
 * <p>This package contains the API-facing building blocks used by tests:
 * typed clients for specific resources (e.g., {@code AuthorsClient}), a factory that
 * creates and configures a shared {@link com.microsoft.playwright.APIRequestContext},
 * and any test-only configuration utilities.</p>
 *
 * <h2>Goals</h2>
 * <ul>
 *   <li><strong>Encapsulation:</strong> hide low-level HTTP details behind intent-based methods
 *       (e.g., {@code getAuthors()}, {@code createBook(...)}) so tests read clearly.</li>
 *   <li><strong>Consistency:</strong> centralize base URL, headers, timeouts, and auth in one place
 *       (the {@code ApiFactory}) to avoid duplication and drift.</li>
 *   <li><strong>Testability:</strong> make it easy for JUnit tests and extensions to obtain
 *       clients and the underlying request context.</li>
 * </ul>
 *
 * <h2>Typical Contents</h2>
 * <ul>
 *   <li>{@code ApiFactory} – builds and owns the {@link com.microsoft.playwright.APIRequestContext}
 *       and exposes typed clients.</li>
 *   <li>Typed clients – small wrappers around HTTP endpoints that return parsed Java objects
 *       (maps/POJOs) or {@link com.microsoft.playwright.APIResponse} for advanced assertions.</li>
 *   <li>{@code Config} – reads environment/system properties for base URL, headers, tokens, etc.</li>
 *   <li>JUnit 5 extensions (optional) – wire up lifecycle and parameter injection for tests.</li>
 * </ul>
 *
 * <h2>Design Guidelines</h2>
 * <ul>
 *   <li>Keep clients small and focused per resource; prefer many small clients over a monolith.</li>
 *   <li>Expose intent-revealing methods; avoid leaking raw paths/headers into tests.</li>
 *   <li>Parse response bodies in clients (e.g., via Jackson) and throw meaningful exceptions on errors;
 *       let tests assert on domain-level outcomes.</li>
 *   <li>Do not introduce global mutable state; the factory/extension should own lifecycles.</li>
 *   <li>Keep these classes under {@code src/test/java}; they are test-only utilities.</li>
 * </ul>
 *
 */
package support;

