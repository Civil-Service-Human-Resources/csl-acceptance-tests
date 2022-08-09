package uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.ELearning;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.BasePage;

import java.awt.*;
import java.awt.event.KeyEvent;

@Component
public class BasicGomoPage extends BasePage {

    @Value("${pages.csl-ui.modules.baseUrl}")
    String baseUrl;

    @FindBy(how = How.CSS, using = "#exitL > .buttonTextC")
    protected WebElement exitLink;

    public void scrollToBottom() {
        WebElement bottomElem = driver.findElement(By.xpath("//span[contains(text(), 'Back to top')]"));
        scrollToElement(bottomElem);
    }

    public void quit() {
        exitLink.click();
    }

    public void confirmQuit() {
        driver.switchTo().alert().accept();
    }

    @Override
    protected String getBaseUrl() {
        return baseUrl;
    }
}
