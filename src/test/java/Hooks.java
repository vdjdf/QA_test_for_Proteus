
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;


import java.nio.file.Paths;

public class Hooks {

    private static final String HTML_FILE_URL =
            "file:///" + Paths.get("src", "test", "resources", "qa-test.html")
                    .toAbsolutePath()
                    .toString()
                    .replace("\\", "/");

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = System.getenv("CI") != null;
        Configuration.timeout = 5000;
        Selenide.open(HTML_FILE_URL);
    }


    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}