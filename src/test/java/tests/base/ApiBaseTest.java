package tests.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import support.ApiFactory;
import support.AuthorsClient;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ApiBaseTest {
    protected ApiFactory api;
    protected AuthorsClient authors;

    @BeforeAll
    void setUp() {
        api = ApiFactory.create();
        authors = api.authorsClient();
    }

    @AfterAll
    void tearDown() {
        if (api != null) api.close();
    }
}

