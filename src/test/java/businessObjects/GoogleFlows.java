package businessObjects;

import com.microsoft.playwright.Page;
import pageObjects.GoogleHomePage;
import pageObjects.GoogleResultsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleFlows {
    private final Page page;

    public GoogleFlows(Page page) {
        this.page = page;
    }

    /** One-call scenario: open Google, search, verify we landed on results and title looks right. */
    public void navigateAndVerifyNavigation(String query, String expectedInTitle) {
        GoogleResultsPage results = new GoogleHomePage(page)
                .navigate().search(query);
        assertTrue(results.isLoaded(), "Results page did not load (URL doesn't contain /search).");
        assertTrue(results.titleContains(expectedInTitle),
                "Title didn't contain expected text. Actual: " + results.title());
    }
}
