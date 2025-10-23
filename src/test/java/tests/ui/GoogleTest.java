package tests.ui;


import tests.base.BaseTest;
import businessObjects.GoogleFlows;
import org.junit.jupiter.api.Test;

public class GoogleTest extends BaseTest {

    @Test
    void googleSearch_basicFlow() {
        new GoogleFlows(page).navigateAndVerifyNavigation("Playwright Java", "playwright");
    }

    
}
