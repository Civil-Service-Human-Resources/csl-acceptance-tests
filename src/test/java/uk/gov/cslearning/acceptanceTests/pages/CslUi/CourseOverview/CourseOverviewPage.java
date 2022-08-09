package uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseOverview;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.Components.DisciteStatus;
import uk.gov.cslearning.acceptanceTests.Components.ModuleDiscite;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CslUiBasePage;

import java.util.List;

@Component
@Slf4j
public class CourseOverviewPage extends CslUiBasePage {

    @Value("${pages.csl-ui.modules.video.load-timeout}")
    private int videoTimeoutMs;

    @Value("${pages.csl-ui.modules.file.load-timeout}")
    private int fileTimeoutMs;

    @Value("${pages.csl-ui.modules.elearning.load-timeout}")
    private int elearningTimeoutMs;

    @FindAll({@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Start")})
    protected List<WebElement> startLinks;

    @FindAll({@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Download document")})
    protected List<WebElement> downloadLinks;

    protected WebElement getModuleDisciteElem(String moduleTitle) {
        WebElement heading = driver.findElement(By.xpath(String.format("//h3[contains(text(), '%s')]", moduleTitle)));
        return heading.findElement(By.xpath("..")).findElement((By.xpath("..")));
    }

    public ModuleDiscite getModuleDiscite(String moduleTitle) {
        WebElement discite = getModuleDisciteElem(moduleTitle);
        return ModuleDiscite.fromWebElement(discite);
    }

    public void startModuleWithUrl(String courseId, String moduleId) {
        navigate(String.format("%s/courses/%s/%s", this.baseUrl, courseId, moduleId));
    }

    public void assertModuleStatus(String moduleTitle, DisciteStatus expectedStatus, int maxRetries) {
        ModuleDiscite moduleDiscite = getModuleDiscite(moduleTitle);
        boolean success = false;
        int tries = 0;
        while (!success && tries < maxRetries) {
            success = switch (expectedStatus) {
                case COMPLETED -> moduleDiscite.isCompleted();
                case JUST_ADDED -> moduleDiscite.isJustAdded();
                case IN_PROGRESS -> moduleDiscite.isInProgress();
            };
            if (!success) {
                log.info("Tried getting module status but failed, refreshing...");
                wait(1000);
                refresh();
                tries++;
                moduleDiscite = getModuleDiscite(moduleTitle);
            }
        }
        if (!success) {
            throw new AssertionError(String.format("Module status on overview page was not '%s', was '%s'", expectedStatus, moduleDiscite.status));
        }
    }

    public void startModule(String moduleTitle) {
        ModuleDiscite discite = getModuleDiscite(moduleTitle);
        discite.startLink.click();
        if (discite.isLink()) {
            closeTab(1);
            refresh();
        }
    }

    public void navigateTo(String courseId) {
        navigate(getUrl(courseId));
    }

    public String getUrl(String courseId) {
        return String.format("%s/courses/%s", this.baseUrl, courseId);
    }
}
