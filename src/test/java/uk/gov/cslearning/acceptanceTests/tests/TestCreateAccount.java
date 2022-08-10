package uk.gov.cslearning.acceptanceTests.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.annotations.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.Utils.LoginUtilityService;
import uk.gov.cslearning.acceptanceTests.Utils.UserManagementService;
import uk.gov.cslearning.acceptanceTests.pages.CslIdentity.LoginPage;
import uk.gov.cslearning.acceptanceTests.pages.CslIdentity.SignUp.SignUpCodePage;
import uk.gov.cslearning.acceptanceTests.pages.CslIdentity.SignUp.SignUpPage;
import uk.gov.cslearning.acceptanceTests.pages.CslIdentity.SignUp.SignUpPasswordPage;
import uk.gov.cslearning.acceptanceTests.pages.CslIdentity.SignUp.SignUpRequestPage;

@SeleniumTest
public class TestCreateAccount {

    String email = "acceptance-test@cabinetoffice.gov.uk";
    String password = "Password123";

    @Autowired
    LoginPage loginPage;

    @Autowired
    SignUpPage signUpPage;

    @Autowired
    SignUpRequestPage signUpRequestPage;

    @Autowired
    SignUpCodePage signUpCodePage;

    @Autowired
    SignUpPasswordPage signUpPasswordPage;

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    LoginUtilityService loginUtilityService;

    @AfterAll
    public void afterAll() {
        userManagementService.deleteUser(email);
    }

    @Test
    public void testAccountCreationSuccess() {
        loginUtilityService.trySignOut();
        loginPage.navigateTo();
        loginPage.goToCreateAccount();
        signUpPage.signUp(email);
        signUpRequestPage.assertHeading("We've sent you an email");
        String signupCode = userManagementService.getSignupCodeForUser(email);
        signUpCodePage.navigateTo(signupCode);
        signUpPasswordPage.completeSignup(password);
        signUpPasswordPage.assertHeading("Password creation complete");
    }


}
