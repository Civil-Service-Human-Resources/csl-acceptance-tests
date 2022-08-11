package uk.gov.cslearning.acceptanceTests.page.CslUi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CourseCompletePage extends CslUiBasePage {

    @FindBy(how = How.LINK_TEXT, using = "learning record")
    protected WebElement learningRecordLink;

    @FindBy(how = How.CSS, using = ".govuk-box-highlight")
    protected WebElement notificationBox;

    public String getCourseAndModuleTitle() {
        WebElement strongTag = notificationBox.findElement(By.tagName("strong"));
        return strongTag.getText();
    }

    public void assertCourseTitleAndModuleTitle(String courseTitle, String moduleTitle) {
        String titles = getCourseAndModuleTitle();
        String expected = String.format("%s (%s)", moduleTitle, courseTitle);
        Assert.isTrue(titles.equals(expected), String.format("Titles on course completion screen do not match. Expected: '%s', actual: '%s'", expected, titles));
    }

    public void clickLearningRecordLink() {
        learningRecordLink.click();
    }

    public void navigateTo(String courseId, String moduleId) {
        navigate(String.format("%s/learning-record/%s/%s", baseUrl, courseId, moduleId));
    }
}
