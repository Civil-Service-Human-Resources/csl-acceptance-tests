package uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.ModuleCreation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

/**
 * Covers adding link modules for:
 * - Websites
 * - Youtube videos
 */
@Component
public class AddLinkModulePage extends AddModuleBasePage {

    @FindBy(how = How.ID, using = "url")
    protected WebElement linkTxt;

    public void setLink(String url) {
        linkTxt.sendKeys(url);
    }

}
