package uk.gov.cslearning.acceptanceTests.page.CslUi;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import uk.gov.cslearning.acceptanceTests.page.BasePage;

@Component
public class CslUiBasePage extends BasePage {

    @Value("${pages.csl-ui.baseUrl}")
    protected
    String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    @FindBy(how = How.LINK_TEXT, using = "Suggestions for you")
    protected WebElement suggestionsForYouLink;

    @FindBy(how = How.LINK_TEXT, using = "Learning record")
    protected WebElement learningRecordLink;

    @FindBy(how = How.LINK_TEXT, using = "Profile")
    protected WebElement profileLink;

    @FindBy(how = How.LINK_TEXT, using = "Skills")
    protected WebElement skillsLink;

    @FindBy(how = How.LINK_TEXT, using = "Admin")
    protected WebElement adminLink;

    @FindBy(how = How.LINK_TEXT, using = "Sign out")
    protected WebElement signOutLink;

    public void assertIsSignedIn() {
        Assert.isTrue(isSignedIn(), "Not signed in");
    }

    public boolean isSignedIn() {
        try {
            signOutLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
