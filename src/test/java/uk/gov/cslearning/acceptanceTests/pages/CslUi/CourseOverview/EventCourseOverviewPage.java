package uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseOverview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventCourseOverviewPage extends CourseOverviewPage {

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "View availability")
    protected WebElement findAvailabilityBtn;

    public void findAvailability() {
        findAvailabilityBtn.click();
    }
}
