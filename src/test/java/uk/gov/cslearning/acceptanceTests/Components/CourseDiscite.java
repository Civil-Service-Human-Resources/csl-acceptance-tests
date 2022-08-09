package uk.gov.cslearning.acceptanceTests.Components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Slf4j
public class CourseDiscite extends BaseDiscite {

    public String description;
    public String type;
    public String duration;
    public String cost;
    public WebElement moveToLearnerRecordLink;
    public WebElement iDidNotAttendLink;

    public CourseDiscite(String description, String type, String duration, String cost, String status,
                        WebElement moveToLearnerRecordLink, WebElement iDidNotAttendLink) {
        super(status);
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.duration = duration;
        this.moveToLearnerRecordLink = moveToLearnerRecordLink;
        this.iDidNotAttendLink = iDidNotAttendLink;
    }

    @Override
    public String getDisciteType() {
        return "course";
    }

    public static CourseDiscite fromWebElement(WebElement disciteItem) {
        String description = disciteItem.findElement(By.cssSelector(".discite__desc")).getText();
        String type = disciteItem.findElement(By.cssSelector(".lpg-course-type")).getText();
        String duration = disciteItem.findElement(By.cssSelector(".lpg-course-duration")).getText();
        String cost = "";
        try {
            cost = disciteItem.findElement(By.cssSelector(".lpg-course-cost")).getText();
        } catch (Exception e) {
            log.debug("Course didn't have a cost element");
        }
        String status = disciteItem.findElement(By.cssSelector(".badge")).getText();

        WebElement moveToLearnerRecordLink = null;
        WebElement iDidNotAttendLink = null;

        try {
            moveToLearnerRecordLink = disciteItem.findElement(By.linkText("Move to Learner Record"));
            iDidNotAttendLink = disciteItem.findElement(By.linkText("I did not attend"));
        } catch (NoSuchElementException e) {
            log.warn("Tried to find 'move to laerner record' and 'I did not attend' links on course but didn't");
        }

        return new CourseDiscite(description, type, duration, cost, status, moveToLearnerRecordLink, iDidNotAttendLink);
    }
}
