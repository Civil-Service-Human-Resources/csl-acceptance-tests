package uk.gov.cslearning.acceptanceTests.pages.CslManagement.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.Components.Management.EventAttendeeListElem;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CslManagementBasePage;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventOverviewPage extends CslManagementBasePage {

    @FindBy(how = How.CSS, using = "ul.attendees.list")
    protected  WebElement attendeesList;

    @FindBy(how = How.ID, using = "learnerEmail")
    protected WebElement inviteEmailTxt;

    @FindBy(how = How.CSS, using = ".govuk-button")
    protected  WebElement inviteBtn;

    public void inviteUser(String userEmail) {
        inviteEmailTxt.sendKeys(userEmail);
        inviteBtn.click();
    }

    private List<EventAttendeeListElem> getEventAttendees() {
        List<WebElement> attendeesLiElems = attendeesList.findElements(By.cssSelector("li.list-details"));
        return attendeesLiElems.stream().map(EventAttendeeListElem::fromWebElement).collect(Collectors.toList());
    }

    public void viewAttendeeOverview(String userEmail) {
        List<EventAttendeeListElem> eventAttendeeElems = getEventAttendees();
        EventAttendeeListElem userElem = null;
        for (EventAttendeeListElem elem : eventAttendeeElems) {
            if (elem.email.equals(userEmail)) {
                userElem = elem;
            }
        }
        if (userElem == null) {
            throw new RuntimeException(String.format("Email '%s' not found on event overview page", userEmail));
        } else {
            userElem.viewLink.click();
        }
    }

    public void navigateTo(String courseId, String moduleId, String eventId) {
        navigate(String.format("%s/content-management/courses/%s/modules/%s/events-overview/%s", this.baseUrl, courseId, moduleId, eventId));
    }

}
