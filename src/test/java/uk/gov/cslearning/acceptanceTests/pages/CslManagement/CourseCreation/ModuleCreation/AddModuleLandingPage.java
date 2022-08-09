package uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.ModuleCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.ModuleType;
import uk.gov.cslearning.acceptanceTests.Components.Management.ModuleListDetails;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CslManagementBasePage;

@Component
public class AddModuleLandingPage extends CslManagementBasePage {

    @FindBy(how = How.ID, using = "modules")
    protected WebElement moduleSelect;

    @FindBy(how = How.CSS, using = ".button-blue")
    protected WebElement addItemBtn;

    public void addModule(ModuleType type) {
        Select modulesDropDown = new Select(moduleSelect);
        switch (type) {
            case FACETOFACE -> modulesDropDown.selectByVisibleText("Face-to-face");
            case ELEARNING -> modulesDropDown.selectByVisibleText("E-learning");
            case YOUTUBE -> modulesDropDown.selectByVisibleText("Video (YouTube)");
            case LINK -> modulesDropDown.selectByVisibleText("Link");
            case MP4 -> modulesDropDown.selectByVisibleText("Video (MP4)");
            case FILE -> modulesDropDown.selectByVisibleText("File (PDF, Word, Excel, PowerPoint)");
        }
        addItemBtn.click();
    }

    public ModuleListDetails getModuleDetails(String moduleTitle) {
        WebElement listRow = driver.findElement(By.xpath(String.format("//p[contains(text(), '%s')]", moduleTitle)));
        return ModuleListDetails.fromWebElement(listRow);
    }

    public void navigateTo(String courseId) {
        navigate(String.format("%s/content-management/courses/%s/add-module", this.baseUrl, courseId));
    }
}
