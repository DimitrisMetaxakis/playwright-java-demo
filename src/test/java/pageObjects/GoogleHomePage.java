package pageObjects;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import enums.KeyAction;

public class GoogleHomePage extends BasePage {
    private static final String BASE_URL = "https://www.google.com";
    private static final String SEARCH_ROLE_NAME = "Search";
    private static final String SEL_SEARCHBOX_CSS = ":is(textarea,input)[aria-label='Search'], input[name='q']";

    private final CookieConsent cookieConsent;

    public GoogleHomePage(Page page) {
        super(page);
        this.cookieConsent = new CookieConsent(page);
    }

    /**
     * Navigates to the Google home page and attempts to dismiss the cookie consent banner if present.
     *
     * <p>Uses the base {@code gotoUrl} helper and a best-effort {@code CookieConsent#tryDismiss()} to keep
     * tests resilient across locales/variants.</p>
     *
     * @return this page object for fluent chaining
     */
    public GoogleHomePage navigate() {
        gotoUrl(BASE_URL);
        cookieConsent.tryDismiss();
        return this;
    }

    /**
     * Performs a search from the Google home page.
     *
     * <p>Locates the search box (by ARIA role first, then CSS fallback), waits for it to be visible, fills the
     * provided query, submits with Enter, and waits until the URL indicates the results page.</p>
     *
     * @param query the search text to submit
     * @return a {@link GoogleResultsPage} representing the results view
     * @throws IllegalStateException if the search box cannot be located or interacted with
     */
    public GoogleResultsPage search(String query) {
        Locator box = getSearchBox();
        waitVisible(box);
        box.fill(query);
        box.press(KeyAction.ENTER.code());
        expectUrlContains("/search");
        return new GoogleResultsPage(page());
    }

    /**
     * Resolves the search box element on the page.
     *
     * <p>Tries an ARIA role-based locator (textbox named "Search") for accessibility-friendly selection.
     * If no such element is present, falls back to a CSS locator that matches common Google search inputs
     * (textarea/input with {@code aria-label='Search'} or {@code input[name='q']}).</p>
     *
     * @return a {@link com.microsoft.playwright.Locator} that targets the search input; the locator may match
     *         zero or more nodes—callers should verify visibility/existence before interacting
     */
    private Locator getSearchBox() {
        Locator roleBox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(SEARCH_ROLE_NAME));
        if (roleBox.count() > 0)
            return roleBox;
        return page.locator(SEL_SEARCHBOX_CSS);
    }


}
