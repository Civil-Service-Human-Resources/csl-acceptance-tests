package uk.gov.cslearning.acceptanceTests.page.CslUi.CourseOverview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileCourseOverviewPage extends CourseOverviewPage {

    @Value("${pages.csl-ui.modules.file.load-timeout}")
    private int timeoutMs;

    public void start(String documentName) {
        WebElement startLink = driver.findElement(By.linkText(documentName));
        startLink.click();
        try {
            Thread.sleep(timeoutMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(String.format("Exception whilst waiting for file to download: %s", e.getMessage()));
        }
    }
}
