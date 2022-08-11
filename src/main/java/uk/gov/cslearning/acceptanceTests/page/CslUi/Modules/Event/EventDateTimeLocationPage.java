package uk.gov.cslearning.acceptanceTests.page.CslUi.Modules.Event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CslUiBasePage;

@Component
public class EventDateTimeLocationPage extends CslUiBasePage {

    @FindBy(how = How.CSS, using = "button")
    protected WebElement continueBtn;

    public void selectEvent(String eventId) {
        WebElement selectRad = driver.findElement(By.id(String.format("radio-%s", eventId)));
        selectRad.click();
        scrollToElement(continueBtn);
        continueBtn.click();
    }
}
