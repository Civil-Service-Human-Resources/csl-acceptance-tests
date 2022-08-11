package uk.gov.cslearning.acceptanceTests.component.Management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ModuleListDetails {

    public String title;
    public String type;
    public String id;

    public ModuleListDetails(String title, String type, String id) {
        this.title = title;
        this.type = type;
        this.id = id;
    }

    public static ModuleListDetails fromWebElement(WebElement listRow) {
        String id = listRow.getAttribute("id");
        String title = listRow.findElement(By.cssSelector(".title")).getText();
        String type = listRow.findElement(By.cssSelector(".type")).getText();
        return new ModuleListDetails(title, type, id);
    }
}
