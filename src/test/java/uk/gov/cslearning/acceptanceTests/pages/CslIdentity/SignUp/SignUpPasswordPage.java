package uk.gov.cslearning.acceptanceTests.pages.CslIdentity.SignUp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.CslIdentity.CslIdentityBasePage;

@Component
public class SignUpPasswordPage extends CslIdentityBasePage {

    @FindBy(how = How.ID, using = "password")
    protected WebElement passwordTxt;

    @FindBy(how = How.ID, using = "passwordConfirm")
    protected WebElement confirmPasswordTxt;

    @FindBy(how = How.CSS, using = ".button")
    protected WebElement continueButton;

    public void completeSignup(String password) {
        passwordTxt.sendKeys(password);
        confirmPasswordTxt.sendKeys(password);
        continueButton.click();
    }

}
