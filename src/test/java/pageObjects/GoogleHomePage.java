package pageObjects;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import enums.KeyAction;


public class GoogleHomePage extends BasePage{

    private static final String BASE_URL = "https://www.google.com";

    private static final String SEL_CONSENT_IFRAME = "iframe[src*='consent']";
    private static final String SEL_ACCEPT_ALL_BUTTON =
            "button:has-text('Accept all'), button:has-text('Agree'), button:has-text('Αποδοχή όλων')";
    private static final String SEARCH_ROLE_NAME = "Search";
    private static final String SEL_SEARCHBOX_CSS = ":is(textarea,input)[aria-label='Search'], input[name='q']";
    private static final String RESULTS_URL_GLOB = "**/search?**";

    private static final int CONSENT_PEEK_MS = 1500;

    public GoogleHomePage(Page page) {
        super(page);
    }

    public GoogleHomePage navigate() {
        page.navigate(BASE_URL);
        acceptCookiesIfPresent();
        return this;
    }

    public GoogleResultsPage search(String query) {

            Locator box = getSearchBox();
        waitVisible(box);
        box.fill(query);
        box.press(KeyAction.ENTER.code());
        page.waitForURL(RESULTS_URL_GLOB);
        return new GoogleResultsPage(page);
    }

    // ===== Private helpers ===== //


    private void acceptCookiesIfPresent() {
        page.waitForTimeout(CONSENT_PEEK_MS);
        for (Frame frame : page.frames()) {
            Locator accept = frame.locator(SEL_ACCEPT_ALL_BUTTON);
            if (accept.count() > 0) {
                safeClick(accept.first());
                return;
            }
        }
    }

    private Locator getSearchBox() {
        Locator roleBox = page.getByRole(
                AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName(SEARCH_ROLE_NAME)
        );
        if (roleBox.count() > 0) return roleBox;

        return page.locator(SEL_SEARCHBOX_CSS);
    }




}
