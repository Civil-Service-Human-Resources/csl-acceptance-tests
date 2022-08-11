package uk.gov.cslearning.acceptanceTests.page.CslManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CslUiBasePage;

public class CslManagementBasePage extends CslUiBasePage {

    @Value("${pages.csl-management.baseUrl}")
    protected
    String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    @FindBy(how = How.LINK_TEXT, using = "Reporting")
    protected WebElement reportingLink;

}
