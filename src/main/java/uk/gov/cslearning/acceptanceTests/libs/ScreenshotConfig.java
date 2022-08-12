package uk.gov.cslearning.acceptanceTests.libs;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScreenshotConfig {

    final WebDriver webDriver;

    @Value("${report.dir}")
    private String reportDir;

    public ScreenshotConfig(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Bean
    public ScreenShotter getScreenshotter() {
        return new ScreenShotter(
                reportDir + "/screenshots",
                webDriver
        );
    }
}
