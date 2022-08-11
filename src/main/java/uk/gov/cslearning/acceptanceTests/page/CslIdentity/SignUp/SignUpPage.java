package uk.gov.cslearning.acceptanceTests.page.CslIdentity.SignUp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.CslIdentityBasePage;

@Component
public class SignUpPage extends CslIdentityBasePage {

    @FindBy(how = How.ID, using = "email")
    protected WebElement emailTxt;

    @FindBy(how = How.ID, using = "confirmEmail")
    protected WebElement confirmEmailTxt;

    @FindBy(how = How.CSS, using = ".button")
    protected WebElement continueButton;

    public void signUp(String email) {
        emailTxt.sendKeys(email);
        confirmEmailTxt.sendKeys(email);
        continueButton.click();
    }

    public void navigateTo() {
        navigate(String.format("%s/signup/request", this.baseUrl));
    }
}
