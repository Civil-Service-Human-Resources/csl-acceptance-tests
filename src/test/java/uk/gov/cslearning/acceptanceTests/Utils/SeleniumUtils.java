package uk.gov.cslearning.acceptanceTests.Utils;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.libs.webDriver.WebDriverLibrary;

@Component
public class SeleniumUtils {

    @Autowired
    WebDriver driver;

    /**
     * Generic wait function for when the tests run a little
     * too fast for the backend to finish API calls etc.
     * @param timeoutMs
     */
    public void wait(int timeoutMs) {
        try {
            Thread.sleep(timeoutMs);
        } catch (InterruptedException e) {
            throw new RuntimeException("Exception waiting");
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
