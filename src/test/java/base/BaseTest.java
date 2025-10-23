package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.nio.file.Path;
import java.nio.file.Paths;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    void globalSetup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );
    }

    @AfterAll
    void globalTeardown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void createContextAndPage(TestInfo info) {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280, 800));
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        page = context.newPage();
    }

    @AfterEach
    void closeContextAndSaveTrace(TestInfo info) {
        String safeName = info.getDisplayName().replaceAll("[^a-zA-Z0-9-_\\.]", "_");
        Path tracePath = Paths.get("build", "traces", safeName + ".zip");
        context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
        context.close();
    }
}

