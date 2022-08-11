package uk.gov.cslearning.acceptanceTests.page.CslUi.CourseOverview;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class EventCourseOverviewPage extends CourseOverviewPage {

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "View availability")
    protected WebElement findAvailabilityBtn;

    public void findAvailability() {
        findAvailabilityBtn.click();
    }
}
