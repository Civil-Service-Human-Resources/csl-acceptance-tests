package uk.gov.cslearning.acceptanceTests.pages.CslIdentity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends CslIdentityBasePage {

    @FindBy(how = How.ID, using = "email-address")
    protected WebElement usernameTxt;

    @FindBy(how = How.ID, using = "password")
    protected WebElement passwordTxt;

    @FindBy(how = How.CSS, using = ".button")
    protected WebElement loginBtn;

    @FindBy(how = How.LINK_TEXT, using = "create an account")
    protected WebElement createAccountLink;

    public void login(String username, String password) {
        usernameTxt.sendKeys(username);
        passwordTxt.sendKeys(password);
        loginBtn.click();
    }

    public void goToCreateAccount() {
        createAccountLink.click();
    }

    public void navigateTo() {
        navigate(this.baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
