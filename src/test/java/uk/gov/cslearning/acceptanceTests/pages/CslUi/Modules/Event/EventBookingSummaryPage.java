package uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.Event;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CslUiBasePage;

@Component
public class EventBookingSummaryPage extends CslUiBasePage {

    @FindBy(how = How.CSS, using = ".button")
    protected WebElement continueBtn;

    public void confirmBooking() {
        continueBtn.click();
    }
}
