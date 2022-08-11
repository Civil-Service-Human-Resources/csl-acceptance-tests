package uk.gov.cslearning.acceptanceTests.page.CslManagement.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CslManagementBasePage;

@Component
public class EventAttendeeOverviewPage extends CslManagementBasePage {

    @FindBy(how = How.LINK_TEXT, using = "Cancel Attendee")
    protected WebElement cancelAttendeeLink;

    @FindBy(how = How.CSS, using = ".linkish-button")
    protected WebElement confirmAttendeeLink;

    @FindBy(how = How.CSS, using = "ul.attendees")
    protected WebElement detailsUl;

    private WebElement getBookingStatus() {
        return detailsUl
                .findElement(By.cssSelector("li.list-details.column-view"))
                .findElements(By.tagName("p")).get(1);
    }

    public void assertAttendeeBooked() {
        assertAttendeeBookingStatus("booked");
    }

    private void assertAttendeeBookingStatus(String expectedStatus) {
        WebElement statusElem = getBookingStatus();
        String status = statusElem.getText().toLowerCase();
        expectedStatus = expectedStatus.toLowerCase();
        Assert.isTrue(status.equalsIgnoreCase(expectedStatus), String.format("Expected status to be '%s' but was '%s'", expectedStatus, status));
    }

    public void confirmAttendee() {
        confirmAttendeeLink.click();
    }

    public void cancelAttendee() {
        scrollToElement(cancelAttendeeLink);
        cancelAttendeeLink.click();
    }

    public void navigateTo(String courseId, String moduleId, String eventId, Integer learnerId) {
        navigate(String.format("%s/content-management/courses/%s/modules/%s/events/%s/attendee/%s", this.baseUrl, courseId, moduleId, eventId, learnerId));
    }

}
