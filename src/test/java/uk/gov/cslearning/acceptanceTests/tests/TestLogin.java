package uk.gov.cslearning.acceptanceTests.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.tests.BaseTest;


@SeleniumTest
public class TestLogin extends BaseTest {

    @Autowired
    HomePage homepage;

    @Test
    public void testLoginSuccessful() {
        loginUtilityService.switchToType(UserType.LEARNER);
        homepage.assertIsSignedIn();
        loginUtilityService.signOut();
    }
}
