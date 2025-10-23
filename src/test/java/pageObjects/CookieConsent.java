package pageObjects;

import com.microsoft.playwright.*;

/**
 * Creates a helper for interacting with cookie consent banners on the current page.
 *
 */

public class CookieConsent {
    private final Page page;
    private static final String ACCEPT_BTN =
            "button:has-text('Accept all'), button:has-text('Agree'), button:has-text('Αποδοχή όλων')";

    public CookieConsent(Page page) { this.page = page; }

    /**
     * Attempts to dismiss a cookie consent banner if present.
     *
     * <p>The method first looks for a visible "Accept all"/"Agree"/"Αποδοχή όλων" button on the
     * main document. If not found, it iterates over known iframes and tries the same selector there.
     * The operation is best-effort and avoids arbitrary sleeps.</p>
     *
     * @return {@code true} if a consent banner was detected and successfully dismissed; {@code false} if none was found
     *         or it was not actionable at the time of the check
     */

    public boolean tryDismiss() {
        // Look for buttons on main page first (some variants don't use iframes)
        Locator mainAccept = page.locator(ACCEPT_BTN);
        if (mainAccept.first().isVisible()) {
            mainAccept.first().click();
            return true;
        }

        // Then try known iframes quickly, no arbitrary sleeps
        for (Frame frame : page.frames()) {
            Locator btn = frame.locator(ACCEPT_BTN);
            if (btn.count() > 0 && btn.first().isVisible()) {
                btn.first().click();
                return true;
            }
        }
        return false; // not present -> move on
    }
}

