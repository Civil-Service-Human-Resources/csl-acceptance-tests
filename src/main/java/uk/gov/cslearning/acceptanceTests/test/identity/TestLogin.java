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


@SeleniumTest
public class TestLogin extends IdentityTests {

    @Autowired
    HomePage homepage;

    @Autowired
    LoginPage loginPage;

    @Autowired
    ReactivationPage reactivationPage;

    @AfterAll
    public void afterAll() {
        CSLUser user = userManagementService.getUser(UserType.LEARNER);
        loginUtilityService.trySignOut();
        userManagementService.teardownReactivations(user.email);
    }

    @DisplayName("Test that a user can sign in")
    @Test
    public void testLoginSuccessful() {
        loginUtilityService.switchToType(UserType.LEARNER);
        homepage.assertIsSignedIn();
        loginUtilityService.signOut();
    }

    @DisplayName("Test that a deactivated account cannot log in and the correct message is displayed")
    @Test
    public void testAccountDeactivated() {
        CSLUser user = userManagementService.getUser(UserType.LEARNER);
        userManagementService.deactivateUser(user);
        loginPage.navigateTo();
        loginPage.login(user.email, user.password);
        loginPage.assertError(
                "Your account has been deactivated",
                "You will need to reactivate your account to keep using Civil Service Learning."
        );
        userManagementService.activateUser(user);
    }

    @DisplayName("Test that the correct message is shown for a deactivated account with a pending reactivation")
    @Test
    public void testPendingReactivation() {
        CSLUser user = userManagementService.getUser(UserType.LEARNER);
        userManagementService.deactivateUser(user);
        userManagementService.createReactivationForUser(user, getToday());
        loginPage.navigateTo();
        loginPage.login(user.email, user.password);
        loginPage.assertError(
                "Your account is pending reactivation",
                "We recently sent you a message to reactivate your account. Please check your emails (including the junk/spam folder)"
        );
    }

    @DisplayName("Test that the user can sign in after reactivating their account")
    @Test
    public void testSelfServeReactivation() {
        CSLUser user = userManagementService.getUser(UserType.LEARNER);
        userManagementService.deactivateUser(user);
        loginPage.navigateTo();
        loginPage.login(user.email, user.password);
        loginPage.clickLink("reactivate your account");
        loginPage.assertHeading("We've sent you an email");
        String reactivationCode = userManagementService.getReactivationCode(user.email);
        reactivationPage.navigateTo(reactivationCode);
        reactivationPage.assertHeading("Account reactivation complete");
        reactivationPage.clickLink("login");
        loginPage.login(user.email, user.password);
        homepage.assertIsSignedIn();
    }
}
