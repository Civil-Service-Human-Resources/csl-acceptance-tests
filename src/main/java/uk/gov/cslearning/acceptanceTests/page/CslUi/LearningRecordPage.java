package uk.gov.cslearning.acceptanceTests.page.CslUi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import uk.gov.cslearning.acceptanceTests.component.LearningRecordTableRow;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LearningRecordPage extends CslUiBasePage {

    public LearningRecordTableRow getLearningRecordRow(String courseTitle) {
        WebElement element = driver.findElement(By.linkText(courseTitle));
        WebElement tr = element.findElement(By.xpath("..")).findElement(By.xpath(".."));
        List<WebElement> tds = tr.findElements(By.tagName("td"));
        String type = tds.get(1).getText();
        String duration = tds.get(2).getText();
        String dateCompleted = tds.get(3).getText();
        return new LearningRecordTableRow(courseTitle, type, duration, dateCompleted);
    }

    public void assertCourseComplete(String courseTitle) {
        WebElement heading = driver.findElement(By.linkText(courseTitle));
        Assert.isTrue(heading.isDisplayed(), String.format("Course title '%s' was not found on learning record", courseTitle));
    }

    public void assertCourseCompleteWithDate(String courseTitle, LocalDateTime date) {
        String expectedDate = date.format(this.dateFormat);
        LearningRecordTableRow tableRow = getLearningRecordRow(courseTitle);
        Assert.isTrue(expectedDate.equals(tableRow.dateCompleted), String.format("Expected completion date of '%s' but found '%s'", expectedDate, tableRow.dateCompleted));
    }

    public void navigateTo() {
        navigate(String.format("%s/learning-record", this.baseUrl));
    }
}
