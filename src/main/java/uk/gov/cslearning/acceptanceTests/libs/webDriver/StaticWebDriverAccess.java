package uk.gov.cslearning.acceptanceTests.libs.webDriver;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class StaticWebDriverAccess {

    public static WebDriver webDriver;

    public StaticWebDriverAccess(WebDriver webDriver) {
        StaticWebDriverAccess.webDriver = webDriver;
    }
}
