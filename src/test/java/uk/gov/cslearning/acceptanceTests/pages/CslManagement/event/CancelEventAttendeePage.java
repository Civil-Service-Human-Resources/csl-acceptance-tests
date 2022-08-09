package uk.gov.cslearning.acceptanceTests.pages.CslManagement.event;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CslManagementBasePage;

@Component
public class CancelEventAttendeePage extends CslManagementBasePage {

    @FindBy(how = How.ID, using = "cancellationReason")
    protected WebElement cancellationSelectElem;

    @FindBy(how = How.CSS, using = ".govuk-button")
    protected WebElement confirmCancelBtn;

    public void confirmCancelAttendee(AdminEventCancellationReason reason) {
        Select cancellationSelect = new Select(cancellationSelectElem);
        switch (reason){
            case BOOKING_NOT_PAID -> cancellationSelect.selectByVisibleText("the booking has not been paid");
            case LEARNER_REQUESTED -> cancellationSelect.selectByVisibleText("the learner has requested that the booking be cancelled");
        }
        confirmCancelBtn.click();
    }

    public void navigateTo(String courseId, String moduleId, String eventId, Integer learnerId) {
        navigate(String.format("%s/content-management/courses/%s/modules/%s/events/%s/attendee/%s/cancel", this.baseUrl, courseId, moduleId, eventId, learnerId));
    }

}
