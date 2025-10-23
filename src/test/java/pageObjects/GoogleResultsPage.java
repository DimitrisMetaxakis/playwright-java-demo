package pageObjects;

import com.microsoft.playwright.Page;

public class GoogleResultsPage extends BasePage{

    public GoogleResultsPage(Page page) {
        super(page);
    }

    public boolean isLoaded() {
        return page.url().contains("/search");
    }

    public String title() {
        return page.title();
    }

    public boolean titleContains(String expected) {
        return title().toLowerCase().contains(expected.toLowerCase());
    }
}
