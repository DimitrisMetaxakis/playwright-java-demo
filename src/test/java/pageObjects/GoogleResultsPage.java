package pageObjects;

import com.microsoft.playwright.Page;

public class GoogleResultsPage extends BasePage {

    public GoogleResultsPage(Page page) {
        super(page);
    }

    /**
     * Indicates whether the results page is considered loaded.
     *
     * <p>The check is based on the current URL containing {@code "/search"}.</p>
     *
     * @return {@code true} if the URL suggests a Google results page; {@code false} otherwise
     */
    public boolean isLoaded() {
        return page.url().contains("/search");
    }

    /**
     * Returns the current document title of the results page.
     *
     * @return the page title as reported by the browser
     */
    public String title() {
        return page.title();
    }

    /**
     * Case-insensitive check that the results page title contains the expected text.
     *
     * @param expected the substring expected to appear in the page title
     * @return {@code true} if the (lowercased) title contains the (lowercased) {@code expected} value; {@code false} otherwise
     */
    public boolean titleContains(String expected) {
        return title().toLowerCase().contains(expected.toLowerCase());
    }
}
