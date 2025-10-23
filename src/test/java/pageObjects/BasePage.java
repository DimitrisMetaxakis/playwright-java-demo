package pageObjects;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Base class for all Page Objects.
 *
 * <p>Encapsulates a shared {@link com.microsoft.playwright.Page} instance and common UI
 * operations (navigation, waits, assertions, and element helpers) so concrete pages remain
 * small and focused on domain behavior and locators.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li>Hold a reference to the Playwright {@code Page} used by the page object.</li>
 *   <li>Provide resilient navigation helpers (e.g., {@code gotoUrl}, {@code waitForLoadState}).</li>
 *   <li>Offer convenience methods for safe interactions (click/type/fill with waits).</li>
 *   <li>Expose lightweight assertions/guards for “is loaded” checks.</li>
 *   <li>Centralize common timeouts, error messages, and logging.</li>
 * </ul>
 *
 */


public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page) { this.page = page; }
    protected Page page() { return page; }

    // ---- Navigation & guards ----
    protected void gotoUrl(String url) {
        page.navigate(url);
        waitForStable(); // network idle or DOM load, your choice
    }

    protected void expectUrlContains(String fragment) {
        page.waitForURL("**" + fragment + "**");
    }

    protected void waitForStable() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    // ---- Interactions ----
    protected void waitVisible(Locator locator, double timeoutMs) {
        locator.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMs).setState(WaitForSelectorState.VISIBLE));
    }

    protected void waitVisible(Locator locator) {
        waitVisible(locator, SECONDS.toMillis(10));
    }

    protected void safeClick(Locator locator) {
        waitVisible(locator);
        locator.click();
    }

    protected void safeFill(Locator locator, String text) {
        waitVisible(locator);
        locator.fill(text);
    }

}
