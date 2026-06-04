package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

public class Hooks {

    private static final String HTML_FILE_URL =
            "file:///" + Paths.get("src", "test", "resources", "qa-test.html")
                    .toAbsolutePath()
                    .toString()
                    .replace("\\", "/");

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
        Selenide.open(HTML_FILE_URL);
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}