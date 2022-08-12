package uk.gov.cslearning.acceptanceTests.test.identity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.LoginPage;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.ReactivationPage;
import uk.gov.cslearning.acceptanceTests.page.CslUi.HomePage;

public class TestReactivation extends IdentityTests {

    CSLUser user = userManagementService.getUser(UserType.LEARNER);

    @Autowired
    HomePage homepage;

    @Autowired
    LoginPage loginPage;

    @Autowired
    ReactivationPage reactivationPage;

    @AfterEach
    public void afterEach() {
        loginUtilityService.signOut();
        userManagementService.teardownReactivations(user.email);
        userManagementService.activateUser(user);
    }

    @DisplayName("Test that a deactivated account cannot log in and the correct message is displayed")
    @Test
    public void testAccountDeactivated() {
        userManagementService.deactivateUser(user);
        loginPage.navigateTo();
        loginPage.login(user.email, user.password);
        loginPage.assertError(
                "Your account has been deactivated",
                "You will need to reactivate your account to keep using Civil Service Learning."
        );
    }

    @DisplayName("Test that the correct message is shown for a deactivated account with a pending reactivation")
    @Test
    public void testPendingReactivation() {
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
