package uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.ModuleCreation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class AddFaceToFaceModulePage extends AddModuleBasePage {

    @FindBy(how = How.ID, using = "cost")
    protected WebElement costTxt;

    public void setCost(int costInPounds) {
        costTxt.sendKeys(String.valueOf(costInPounds));
    }

}
