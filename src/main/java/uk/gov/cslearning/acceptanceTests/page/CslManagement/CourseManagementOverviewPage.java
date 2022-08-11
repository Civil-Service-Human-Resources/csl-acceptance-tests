package uk.gov.cslearning.acceptanceTests.page.CslManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.component.Management.ModuleContainer;
import uk.gov.cslearning.acceptanceTests.component.Management.ModuleItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CourseManagementOverviewPage extends CslManagementBasePage {

    protected final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy");

    @FindBy(how = How.LINK_TEXT, using = "Preview and edit")
    protected WebElement previewEditLink;

    @FindBy(how = How.CSS, using = ".add-audience-link")
    protected WebElement addAudienceBtn;

    @FindBy(how = How.CSS, using = ".govuk-button")
    protected WebElement publishBtn;

    @FindBy(how = How.LINK_TEXT, using = "Cancel")
    protected WebElement cancelLink;

    private Map<String, ModuleContainer> getEventModules() {
        List<WebElement> moduleContainerElems = driver.findElements(By.cssSelector(".module-container-small"));
        // First elem is not actually a module, it's just a label
        moduleContainerElems.remove(0);
        List<ModuleContainer> modules = moduleContainerElems.stream().map(ModuleContainer::fromWebElement).collect(Collectors.toList());
        Map<String, ModuleContainer> moduleMap = new HashMap<>();
        for (ModuleContainer mc : modules) {
            moduleMap.put(mc.moduleTitle, mc);
        }
        return moduleMap;
    }

    public void viewEventOverview(String moduleTitle, String eventLocation, LocalDateTime eventDate) {
        String eventLocationWithDate = String.format("%s, %s", eventLocation, eventDate.format(this.dateFormat));
        Map<String, ModuleContainer> modules = getEventModules();
        ModuleContainer module = modules.get(moduleTitle);
        if (module != null) {
            Map<String, ModuleItem> events = module.events;
            ModuleItem event = events.get(eventLocationWithDate);
            if (event != null) {
                event.viewLink.click();
            } else {
                throw new RuntimeException(String.format("Event '%s' not found", eventLocationWithDate));
            }
        } else {
            throw new RuntimeException(String.format("Module '%s' not found", moduleTitle));
        }
    }

    public void publishCourse() {
        publishBtn.click();
    }

    public void navigateToEdit() {
        previewEditLink.click();
    }

    public void navigateToAddAudience() {
        addAudienceBtn.click();
    }

    public void navigateTo(String courseId) {
        navigate(String.format("%s/content-management/courses/%s/overview", this.baseUrl, courseId));
    }

}
