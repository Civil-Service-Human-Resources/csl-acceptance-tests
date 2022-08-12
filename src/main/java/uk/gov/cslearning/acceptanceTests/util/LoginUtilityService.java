package uk.gov.cslearning.acceptanceTests.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.LoginPage;
import uk.gov.cslearning.acceptanceTests.page.CslUi.HomePage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to manage all of the various logging in/out in the acceptance tests.
 * This class should always be used when logging in/out, as it has access to
 * the cache, which other tests can use to manage their logins correctly.
 */
@Service
@Slf4j
public class LoginUtilityService {

    final WebDriver driver;

    final Cache cache;

    @Value("${pages.csl-ui.baseUrl}")
    String baseUrl;

    final LoginPage loginPage;

    final HomePage homePage;

    final UserManagementService userManagementService;

    public LoginUtilityService(WebDriver driver, Cache cache, LoginPage loginPage, HomePage homePage, UserManagementService userManagementService) {
        this.driver = driver;
        this.cache = cache;
        this.loginPage = loginPage;
        this.homePage = homePage;
        this.userManagementService = userManagementService;
    }

    private void login(CSLUser user) {
        loginPage.navigateTo();
        loginPage.login(user.email, user.password);
        String loginError = loginPage.getLoginError();
        if (!loginError.isEmpty()) {
            throw new RuntimeException(String.format("Error logging in: \"%s\"", loginError));
        }
        cache.setCurrentUser(user);
    }

    public void signOut() {
        try {
            log.info("trying to log out");
            WebElement signOutLink = driver.findElement(By.linkText("Sign out"));
            signOutLink.click();
        } catch (NoSuchElementException e) {
            log.info("Sign out button not found.");
        }
        cache.clearCurrentUser();
    }

    public synchronized void switchToType(UserType type) {
        if (!homePage.isSignedIn() &&
                cache.getCurrentUser() == null ||
                !cache.getCurrentUser().type.equals(type)) {
            log.info(String.format("Switching user to type %s", type.toString()));
            CSLUser user = userManagementService.getUser(type);
            if (user == null) {
                throw new RuntimeException(String.format("User of type %s does not exist in the cache", type));
            }
            switchUser(user);
        } else {
            log.info(String.format("Didn't need to switch to type %s", type));
        }
    }

    private void switchUser(CSLUser user) {
        try {
            signOut();
        } catch (NoSuchElementException e) {
            log.info("Sign out button not found, logging in.");
        }
        login(user);
    }

}
