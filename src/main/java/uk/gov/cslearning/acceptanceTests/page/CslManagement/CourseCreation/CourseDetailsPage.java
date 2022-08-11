package uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CslManagementBasePage;

@Component
public class CourseDetailsPage extends CslManagementBasePage {

    @FindBy(how = How.ID, using = "shortDescription")
    protected WebElement shortDescriptionTxt;

    @FindBy(how = How.ID, using = "description")
    protected WebElement descriptionTxt;

    @FindBy(how = How.ID, using = "learningOutcomes")
    protected WebElement learningOutcomesTxt;

    @FindBy(how = How.ID, using = "preparation")
    protected WebElement prepartionTxt;

    @FindBy(how = How.CSS, using = ".govuk-button")
    protected WebElement continueBtn;

    @FindBy(how = How.LINK_TEXT, using = "Cancel")
    protected WebElement cancelLink;

    public void setCourseDetails(String shortDescription, String description, String learningOutcomes, String preparation) {
        shortDescriptionTxt.sendKeys(shortDescription);
        descriptionTxt.sendKeys(description);
        learningOutcomesTxt.sendKeys(learningOutcomes);
        prepartionTxt.sendKeys(preparation);
        continueBtn.click();
    }

    public void navigateTo() {
        navigate(this.baseUrl);
    }

    public String getPageUrl() {
        return String.format("%s/content-management/courses/details", this.baseUrl);
    }
}
