package uk.gov.cslearning.acceptanceTests.pages.CslUi;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import uk.gov.cslearning.acceptanceTests.Components.CourseDiscite;
import uk.gov.cslearning.acceptanceTests.Components.EventDiscite;
import uk.gov.cslearning.acceptanceTests.Components.ModuleDiscite;

@Component
public class HomePage extends CslUiBasePage {

    @FindBy(how = How.ID, using = "q")
    protected WebElement searchTxt;

    @FindBy(how = How.CSS, using = "search-box__submit")
    protected WebElement searchBtn;

    @FindBy(how = How.LINK_TEXT, using = "Yes, remove course now.")
    protected WebElement removeFromLearningPlanConfirmation;

    private WebElement getCourseDisciteElem(String courseTitle) {
        WebElement courseLink = this.driver.findElement(By.linkText(courseTitle));
        return courseLink
                .findElement(By.xpath(".."))
                .findElement(By.xpath(".."))
                .findElement(By.xpath(".."));
    }

    private WebElement getEventDisciteElem(String moduleTitle) {
        WebElement moduleLink = this.driver.findElement(By.linkText(moduleTitle));
        return moduleLink
                .findElement(By.xpath(".."))
                .findElement(By.xpath(".."))
                .findElement(By.xpath(".."));
    }

    public CourseDiscite getCourse(String courseTitle) {
        return CourseDiscite.fromWebElement(getCourseDisciteElem(courseTitle));
    }

    public EventDiscite getEvent(String moduleTitle) {
        return EventDiscite.fromWebElement(getEventDisciteElem(moduleTitle));
    }

    public void clickCourse(String courseTitle) {
        WebElement courseLink = this.driver.findElement(By.linkText(courseTitle));
        courseLink.click();
    }

    public void search(String query) {
        searchTxt.sendKeys(query);
        searchBtn.click();
    }

    public boolean isCourseOnHomepage (String courseTitle) {
        try {
            driver.findElement(By.linkText(courseTitle));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void assertCourseJustAdded(String courseTitle) {
        CourseDiscite courseStatus = getCourse(courseTitle);
        courseStatus.assertJustAdded();
    }

    public void assertCourseInProgress(String courseTitle) {
        CourseDiscite courseStatus = getCourse(courseTitle);
        courseStatus.assertInProgress();
    }

    public void assertEventRequested(String moduleTitle) {
        EventDiscite eventDiscite = getEvent(moduleTitle);
        eventDiscite.assertRequested();
    }

    public void assertEventConfirmed(String moduleTitle) {
        EventDiscite eventDiscite = getEvent(moduleTitle);
        eventDiscite.assertConfirmed();
    }

    public void assertEventCancelled(String moduleTitle) {
        EventDiscite eventDiscite = getEvent(moduleTitle);
        eventDiscite.assertCancelled();
    }

    public void cancelBooking(String moduleTitle) {
        EventDiscite eventDiscite = getEvent(moduleTitle);
        eventDiscite.cancelBookingLink.click();
    }

    public void completeEventCourse(String courseTitle) {
        CourseDiscite courseDiscite = getCourse(courseTitle);
        courseDiscite.moveToLearnerRecordLink.click();
        WebElement confirmLink = driver.findElement(By.linkText("Yes, add it."));
        confirmLink.click();
    }

    public void didNotAttendEventCourse(String courseTitle) {
        CourseDiscite courseDiscite = getCourse(courseTitle);
        courseDiscite.iDidNotAttendLink.click();
        WebElement confirmLink = driver.findElement(By.linkText("Yes, remove it."));
        confirmLink.click();
    }

    public void removeCourseFromLearningPlan(String courseTitle) {
        WebElement courseDisciteElem = getCourseDisciteElem(courseTitle);
        courseDisciteElem.findElement(By.partialLinkText("Remove")).click();
    }

    public void confirmRemoveCourseFromLearningPlan() {
        removeFromLearningPlanConfirmation.click();
    }

    public void navigateTo() {
        this.navigate(getPageUrl());
    }

    public String getPageUrl() {
        return String.format("%s/home", this.baseUrl);
    }
}
