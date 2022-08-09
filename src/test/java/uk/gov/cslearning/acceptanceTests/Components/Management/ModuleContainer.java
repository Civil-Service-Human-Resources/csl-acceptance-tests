package uk.gov.cslearning.acceptanceTests.Components.Management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModuleContainer {

    public String moduleTitle;
    public WebElement addEventLink;

    public Map<String, ModuleItem> events;

    public ModuleContainer(String moduleTitle, WebElement addEventLink, Map<String, ModuleItem> events) {
        this.moduleTitle = moduleTitle;
        this.addEventLink = addEventLink;
        this.events = events;
    }

    public static ModuleContainer fromWebElement(WebElement elem) {
        Map<String, ModuleItem> events = new HashMap<>();
        WebElement moduleTileContainer = elem.findElements(By.cssSelector(".title-container")).get(0);
        String moduleTitle = moduleTileContainer.findElement(By.tagName("strong")).getText();
        WebElement addEventLink = moduleTileContainer.findElement(By.linkText("Add new event"));

        List<ModuleItem> moduleItems = elem.findElements(By.cssSelector(".module-item")).stream().map(ModuleItem::fromWebElem).collect(Collectors.toList());
        for (ModuleItem mi : moduleItems) {
            events.put(mi.eventLocationAndDate, mi);
        }
        return new ModuleContainer(moduleTitle, addEventLink, events);
    }
}
