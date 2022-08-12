package uk.gov.cslearning.acceptanceTests.junitExtensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.cslearning.acceptanceTests.libs.ExtentReport.ExtentReporter;
import uk.gov.cslearning.acceptanceTests.util.CourseManagementService;
import uk.gov.cslearning.acceptanceTests.util.UserManagementService;

/**
 * Manage data after all tests have run
 * - Any created courses
 * - Any tokens (to avoid duplicate token errors)
 */
@Slf4j
public class GlobalDataExtension implements AfterAllCallback {

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(extensionContext);
        applicationContext.getBean(CourseManagementService.class).cleanUpCourses();
        applicationContext.getBean(UserManagementService.class).teardownTokens();
    }
}
