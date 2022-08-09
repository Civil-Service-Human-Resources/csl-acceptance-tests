package uk.gov.cslearning.acceptanceTests.libs.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import uk.gov.cslearning.acceptanceTests.annotation.LazyConfiguration;
import uk.gov.cslearning.acceptanceTests.libs.webDriver.annotation.WebdriverScopeBean;

import java.util.Collections;
import java.util.HashMap;

@LazyConfiguration
public class WebDriverLibrary {

    @Value("${browser}")
    private String webBrowser;

    @Bean
    @WebdriverScopeBean
    public WebDriver getDriver() {

        WebDriver driver = null;
        switch (webBrowser) {
            case "Chrome" -> {
                final ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1580,1280");

                final HashMap<String, Object> prefs = new HashMap();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            }
            case "Firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }

}
