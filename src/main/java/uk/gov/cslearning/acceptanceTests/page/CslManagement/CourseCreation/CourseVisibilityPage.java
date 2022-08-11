package uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CslManagementBasePage;

@Component
public class CourseVisibilityPage extends CslManagementBasePage {

    @FindBy(how = How.ID, using = "visibility")
    protected WebElement yesVisibleRad;

    @FindBy(how = How.ID, using = "visibility-2")
    protected WebElement noVisibleRad;

    @FindBy(how = How.CSS, using = ".govuk-button")
    protected WebElement continueBtn;

    @FindBy(how = How.LINK_TEXT, using = "Cancel")
    protected WebElement cancelLink;

    public void setCourseVisibility(boolean visible) {
        if (visible) {
            yesVisibleRad.click();
        } else {
            noVisibleRad.click();
        }
        continueBtn.click();
    }

    public void navigateTo() {
        navigate(String.format("%s/content-management/courses/visibility", this.baseUrl));
    }

}
