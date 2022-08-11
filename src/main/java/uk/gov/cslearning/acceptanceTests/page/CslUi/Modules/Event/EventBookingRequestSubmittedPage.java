package uk.gov.cslearning.acceptanceTests.page.CslUi.Modules.Event;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CslUiBasePage;

@Component
public class EventBookingRequestSubmittedPage extends CslUiBasePage {

    @FindBy(how = How.CSS, using = ".confirmed__heading--caps")
    protected WebElement moduleTitleHeading;

    public void assertBookingConfirmed(String moduleTitle) {
        String actualModuleTitle = moduleTitleHeading.getText();
        Assert.isTrue(actualModuleTitle.equalsIgnoreCase(moduleTitle), String.format("Module title was '%s' but was expected to be '%s'", actualModuleTitle, moduleTitle));
    }
}
