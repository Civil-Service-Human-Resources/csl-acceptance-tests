package uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.ModuleCreation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CslManagementBasePage;

@Component
public class AddModuleBasePage extends CslManagementBasePage {

    @FindBy(how = How.ID, using = "title")
    protected WebElement moduleTitleTxt;

    @FindBy(how = How.ID, using = "description")
    protected WebElement moduleDescriptionTxt;

    @FindBy(how = How.ID, using = "hours")
    protected WebElement hoursTxt;

    @FindBy(how = How.ID, using = "minutes")
    protected WebElement minutesTxt;

    @FindBy(how = How.ID, using = "isOptional")
    protected WebElement optionalCheck;

    @FindBy(how = How.ID, using = "associatedLearning")
    protected WebElement associatedLearningCheck;

    @FindBy(how = How.ID, using = "submitButton")
    protected WebElement addItemBtn;

    public void setTitleAndDescription(String title, String description) {
        moduleTitleTxt.sendKeys(title);
        moduleDescriptionTxt.sendKeys(description);
    }

    public void setDuration(String hours, String minutes) {
        hoursTxt.sendKeys(hours);
        minutesTxt.sendKeys(minutes);
    }

    public void addModule() {
        addItemBtn.click();
    }

}
