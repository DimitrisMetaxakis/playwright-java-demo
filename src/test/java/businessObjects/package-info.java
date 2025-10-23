

/**
 * Business Objects (BO) package.
 *
 * <p><strong>What is a Business Object?</strong><br>
 * A Business Object models a user-intent or domain action at a higher level than a UI/Page or raw API call.
 * Instead of exposing clicks, locators, or endpoints, a BO exposes intent-revealing methods such as
 * {@code loginAs(User)}, {@code searchFor(String)}, or {@code checkout(Order)}. Internally it can orchestrate
 * Page Objects, API clients, data builders, and assertions required to accomplish that action.
 *
 * <p><strong>Why use it as a test design pattern?</strong>
 * <ul>
 *   <li><em>Stability & maintainability</em> — Tests depend on stable domain actions, not fragile UI details.
 *       UI or API changes are absorbed inside BOs with minimal test churn.</li>
 *   <li><em>Readability</em> — Tests read like business workflows, improving intent clarity and reducing noise.</li>
 *   <li><em>Reuse & composition</em> — Common flows (e.g., authenticate → search → add to cart) are reusable,
 *       chainable steps instead of duplicated sequences across test classes.</li>
 *   <li><em>Separation of concerns</em> — Page Objects handle <i>how</i> (locators/interactions); BOs handle
 *       <i>what</i> (business intent) and orchestrate between UI and API layers.</li>
 *   <li><em>Cross-channel testing</em> — BOs can mix UI and API steps behind a single method,
 *       enabling faster, more reliable hybrid flows.</li>
 *   <li><em>Consistent assertions</em> — Business-level invariants (e.g., “user is authenticated”, “order submitted”)
 *       live in one place, ensuring uniform checks across tests.</li>
 * </ul>
 *
 * <p><strong>When to use</strong><br>
 * Adopt BOs whenever tests repeat domain workflows or when you need to shield tests from volatile UI/API details.
 * Keep BO methods focused on a single business intention and name them to reflect real user goals.
 *
 * <p><strong>Guidelines</strong>
 * <ul>
 *   <li>Expose intent-based methods; avoid leaking locators, selectors, or low-level request details.</li>
 *   <li>Delegate UI mechanics to Page Objects and HTTP calls to API clients; the BO coordinates them.</li>
 *   <li>Return meaningful results or state objects, not pages, unless the next step is inherently UI-driven.</li>
 *   <li>Centralize domain-level assertions and data setup to reduce duplication.</li>
 * </ul>
 */


package businessObjects;