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

    /**
     * Performs a Google search and validates basic navigation outcomes.
     * <p>
     * Flow:
     * <ol>
     *   <li>Open the Google home page.</li>
     *   <li>Submit the given search query.</li>
     *   <li>Verify the results page is loaded (URL contains {@code /search}).</li>
     *   <li>Verify the results page title contains the expected text.</li>
     * </ol>
     *
     * @param query            the search term to submit on Google
     * @param expectedInTitle  substring expected to appear in the results page title
     * @throws AssertionError if the results page fails to load or the title does not contain {@code expectedInTitle}
     */
    public void navigateAndVerifyNavigation(String query, String expectedInTitle) {
        GoogleResultsPage results = new GoogleHomePage(page)
                .navigate().search(query);
        assertTrue(results.isLoaded(), "Results page did not load (URL doesn't contain /search).");
        assertTrue(results.titleContains(expectedInTitle),
                "Title didn't contain expected text. Actual: " + results.title());
    }
}
