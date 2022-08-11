package uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.ModuleCreation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Covers uploading:
 * - Files
 * - MP4 videos
 * - E-Learning packages
 */
@Component
public class AddUploadableModulePage extends AddModuleBasePage {

    @Value("${pages.csl-management.module-creation.pdf-file-name}")
    String pdfFileName;

    @FindBy(how = How.ID, using = "file-upload")
    protected WebElement chooseFileBtn;

    @FindBy(how = How.ID, using = "uploadButton")
    protected WebElement uploadFileBtn;

    public void uploadPdf() {
        File file = new File(String.format("src/test/resources/files/%s", pdfFileName));
        String filePath = file.getAbsolutePath();
        chooseFileBtn.sendKeys(filePath);
        uploadFileBtn.click();
    }

}
