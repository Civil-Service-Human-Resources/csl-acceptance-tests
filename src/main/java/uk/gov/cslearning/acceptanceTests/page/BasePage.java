package uk.gov.cslearning.acceptanceTests.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;

@Slf4j
public abstract class BasePage {

    protected final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");

    @Autowired
    public WebDriver driver;

    @PostConstruct
    public void initDriver() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.TAG_NAME, using = "h1")
    protected WebElement heading;

    public void assertHeading(String expected) {
        Assert.isTrue(heading.getText().equals(expected), String.format("Heading was expected to be '%s' but was '%s'", expected, heading.getText()));
    }

    public void assertTextExact(String expectedText, WebElement elem) {
        String actualText = elem.getText();
        Assert.isTrue(expectedText.equals(actualText), String.format("Expected to find '%s' text on element '%s' but found '%s'", expectedText, elem.getTagName(), actualText));
    }

    public void assertTextExactIgnoreCase(String expectedText, WebElement elem) {
        String actualText = elem.getText();
        Assert.isTrue(expectedText.equalsIgnoreCase(actualText), String.format("Expected to find '%s' text but found '%s' (ignoring case)", expectedText, actualText));
    }

    public void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    protected void closeTab(int tabIndex) {
        String[] tabs = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(tabs[tabIndex]);
        driver.close();
        driver.switchTo().window(tabs[0]);
    }

    protected void wait(int timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(String.format("Exception whilst waiting: %s", e.getMessage()));
        }
    }

    public void navigate(String url) {
        log.debug(String.format("Going to %s", url));
        this.driver.navigate().to(url);
        if (this.heading.getText().equalsIgnoreCase("sorry, there is a problem with this service")) {
            throw new RuntimeException("Page failed to load");
        }
    }

    public void scrollToElement(WebElement elem) {
        Actions action = new Actions(driver);
        action.moveToElement(elem).build().perform();
    }

    protected abstract String getBaseUrl();

}
