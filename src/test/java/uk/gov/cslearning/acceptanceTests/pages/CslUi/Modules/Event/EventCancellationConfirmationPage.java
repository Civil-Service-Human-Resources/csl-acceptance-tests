package uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.Event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CslUiBasePage;

@Component
public class EventCancellationConfirmationPage extends CslUiBasePage {

    @FindBy(how = How.CSS, using = ".confirmed__heading--caps")
    protected WebElement moduleTitleHeading;

    public void assertTitle(String expectedCourseTitle) {
        String actualCourseTitle = moduleTitleHeading.getText();
        Assert.isTrue(actualCourseTitle.equalsIgnoreCase(expectedCourseTitle), String.format("Expected course title to be '%s' but was '%s'", expectedCourseTitle, actualCourseTitle));
    }
}
