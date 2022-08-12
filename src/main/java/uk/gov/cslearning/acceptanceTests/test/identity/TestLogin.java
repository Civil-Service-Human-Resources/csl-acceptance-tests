package uk.gov.cslearning.acceptanceTests.test.identity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.LoginPage;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.ReactivationPage;
import uk.gov.cslearning.acceptanceTests.page.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.test.BaseTest;

public class TestLogin extends IdentityTests {

    @Autowired
    HomePage homepage;

    @Autowired
    LoginPage loginPage;

    @DisplayName("Test that a user can sign in")
    @Test
    public void testLoginSuccessful() {
        loginUtilityService.switchToType(UserType.LEARNER);
        homepage.assertIsSignedIn();
        loginUtilityService.signOut();
    }
}
