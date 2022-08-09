package uk.gov.cslearning.acceptanceTests.Components.Management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ModuleItem {

    public String eventLocationAndDate;
    public WebElement viewLink;

    public ModuleItem(String eventLocationAndDate, WebElement viewLink) {
        this.eventLocationAndDate = eventLocationAndDate;
        this.viewLink = viewLink;
    }

    public static ModuleItem fromWebElem(WebElement elem) {
        WebElement tileContainer = elem.findElement(By.cssSelector(".title-container"));
        String evenLocationAndDate = tileContainer.findElements(By.tagName("p")).get(0).getText();
        WebElement viewLink = elem.findElement(By.linkText("View"));
        return new ModuleItem(evenLocationAndDate, viewLink);
    }
}
