package uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CslManagementBasePage;

@Component
public class CourseTitlePage extends CslManagementBasePage {

    @FindBy(how = How.ID, using = "title")
    protected WebElement titleTxt;

    @FindBy(how = How.ID, using = "topicId")
    protected WebElement topicIdTxt;

    @FindBy(how = How.CSS, using = ".govuk-button")
    protected WebElement continueBtn;

    @FindBy(how = How.LINK_TEXT, using = "Cancel")
    protected WebElement cancelLink;

    public void setCourseTitle(String title, String topicId) {
        titleTxt.sendKeys(title);
        topicIdTxt.sendKeys(topicId);
        continueBtn.click();
    }

    public void navigateTo() {
        navigate(this.baseUrl);
    }

    public String getPageUrl() {
        return String.format("%s/content-management/courses/title", this.baseUrl);
    }
}
