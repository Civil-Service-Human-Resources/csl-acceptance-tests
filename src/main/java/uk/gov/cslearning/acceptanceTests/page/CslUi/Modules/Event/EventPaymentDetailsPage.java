package uk.gov.cslearning.acceptanceTests.page.CslUi.Modules.Event;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CslUiBasePage;

@Component
public class EventPaymentDetailsPage extends CslUiBasePage {

    @FindBy(how = How.ID, using = "PURCHASE_ORDER")
    protected WebElement purchaseOrderTxt;

    @FindBy(how = How.CSS, using = ".button")
    protected WebElement continueBtn;

    public void setPurchaseOrder(String PONumber) {
        purchaseOrderTxt.sendKeys(PONumber);
        continueBtn.click();
    }
}
