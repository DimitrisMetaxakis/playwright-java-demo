package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

/** Common Playwright helpers for all Page Objects. */
public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    /** Waits until the locator is visible. */
    protected void waitVisible(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    /** Clicks after ensuring visibility. */
    protected void safeClick(Locator locator) {
        waitVisible(locator);
        locator.click();
    }

    /** Optional: type after visible (handy for reuse). */
    protected void safeFill(Locator locator, String text) {
        waitVisible(locator);
        locator.fill(text);
    }
}
