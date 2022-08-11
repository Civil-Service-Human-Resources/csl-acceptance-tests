package uk.gov.cslearning.acceptanceTests.page.CslManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class CoursePreviewPage extends CslManagementBasePage {

    @FindBy(how = How.LINK_TEXT, using = "Title")
    protected WebElement editTitleLink;

    @FindBy(how = How.LINK_TEXT, using = "Details")
    protected WebElement editDetailsLink;

    @FindBy(how = How.LINK_TEXT, using = "Modules")
    protected WebElement editModulesLink;

    @FindBy(how = How.CSS, using = ".govuk-button")
    protected WebElement returnToCourseBtn;

    @FindBy(how = How.LINK_TEXT, using = "Cancel")
    protected WebElement cancelLink;

    public void navigateToEditTitle() {
        editTitleLink.click();
    }
    public void navigateToEditDetails() {
        editDetailsLink.click();
    }
    public void navigateToEditModules() {
        editModulesLink.click();
    }
    public void returnToCourse() {
        returnToCourseBtn.click();
    }

    public void navigateTo(String courseId) {
        navigate(String.format("%s/content-management/courses/%s/preview", this.baseUrl, courseId));
    }
}
