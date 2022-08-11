package uk.gov.cslearning.acceptanceTests.page.CslIdentity.SignUp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.CslIdentityBasePage;

@Component
public class SignUpRequestPage extends CslIdentityBasePage {

    @FindBy(how = How.TAG_NAME, using = "h2")
    protected WebElement subheading;

}
