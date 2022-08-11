package uk.gov.cslearning.acceptanceTests.page.CslIdentity;

import org.openqa.selenium.By;
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

    @FindBy(how = How.ID, using = "error-summary-heading-example-1")
    protected WebElement errorHeadingyH;

    @FindBy(how = How.CSS, using = ".error-summary")
    protected WebElement errorSummaryDiv;

    public void login(String username, String password) {
        usernameTxt.sendKeys(username);
        passwordTxt.sendKeys(password);
        loginBtn.click();
    }

    public void assertError(String expectedHeading, String expectedMessage) {
        WebElement errorSpan = errorSummaryDiv.findElement(By.tagName("p")).findElement(By.tagName("span"));
        assertTextExact(expectedHeading, errorHeadingyH);
        assertTextExact(expectedMessage, errorSpan);
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
