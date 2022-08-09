package uk.gov.cslearning.acceptanceTests.pages.CslUi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SuggestionsForYouPage extends CslUiBasePage {

    public WebElement getSuggestionTile(String courseTitle) {
        WebElement courseTitleElem = driver.findElement(By.linkText(courseTitle));
        return courseTitleElem.findElement(By.xpath("..")).findElement(By.xpath(".."));
    }

    public void addCourseToLearningPlan(String courseTitle) {
        WebElement suggestionTile = getSuggestionTile(courseTitle);
        suggestionTile.findElement(By.partialLinkText("Add")).click();
    }

    public void removeCourseFromSuggestions(String courseTitle) {
        WebElement suggestionTile = getSuggestionTile(courseTitle);
        suggestionTile.findElement(By.partialLinkText("Remove")).click();
    }

    public void assertCourseRemoved(String courseTitle) {
        WebElement notificationBox = driver.findElement(By.cssSelector(".banner--confirmation"));
        WebElement courseTitleSpan = notificationBox.findElement(By.cssSelector(".banner__heading-large"));
        WebElement tagLine = notificationBox.findElement(By.tagName("strong"));

        Assert.isTrue(courseTitleSpan.getText().equalsIgnoreCase(courseTitle), "Course title is not displayed in banner.");
        Assert.isTrue(tagLine.getText().equalsIgnoreCase("this has been removed from your suggested courses."), "Course removed tagline not displayed.");

    }

    public void navigateTo() {
        navigate(String.format("%s/suggestions-for-you", this.baseUrl));
    }
}
