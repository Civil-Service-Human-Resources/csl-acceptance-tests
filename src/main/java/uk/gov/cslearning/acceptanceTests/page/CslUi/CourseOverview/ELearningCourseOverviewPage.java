package uk.gov.cslearning.acceptanceTests.page.CslUi.CourseOverview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ELearningCourseOverviewPage extends CourseOverviewPage {

    @Value("${pages.csl-ui.modules.elearning.load-timeout}")
    private int timeoutMs;

    public void startModule() {
        WebElement startLink = driver.findElement(By.linkText("Start this learning"));
        startLink.click();
        try {
            Thread.sleep(timeoutMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(String.format("Exception whilst waiting for elearning to download: %s", e.getMessage()));
        }
    }
}
