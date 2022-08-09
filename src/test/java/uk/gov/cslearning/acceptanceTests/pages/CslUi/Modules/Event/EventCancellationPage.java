package uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.Event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CslUiBasePage;

@Component
public class EventCancellationPage extends CslUiBasePage {

    @FindBy(how = How.CSS, using = ".button")
    protected WebElement continueBtn;

    public void setCancellationReason(CancellationReason cancellationReason) {
        String selectorId = "Other work priorities";
        switch (cancellationReason) {
            case ILLNESS -> selectorId = "Illness";
            case FAMILY_BEREAVEMENT -> selectorId = "Family bereavement";
            case OTHER_WORK_PRIORITIES -> selectorId = "Other work priorities";
        }
        WebElement reasonRad = driver.findElement(By.id(selectorId));
        reasonRad.click();
        continueBtn.click();
    }
}
