package uk.gov.cslearning.acceptanceTests.junitExtensions;

import com.aventstack.extentreports.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.cslearning.acceptanceTests.libs.ExtentReport.ExtentReporting;
import uk.gov.cslearning.acceptanceTests.libs.ScreenShotter;
import uk.gov.cslearning.acceptanceTests.libs.webDriver.StaticWebDriverAccess;

@Slf4j
public class ScreenshotExtension implements AfterAllCallback, AfterTestExecutionCallback, BeforeTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        String testName = extensionContext.getDisplayName();
        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        boolean testFailed = extensionContext.getExecutionException().isPresent();
        if (testFailed) {
            String screenshotName = ScreenShotter.takeScreenshot(testMethodName);
            Throwable error = extensionContext.getExecutionException().get();
            ExtentReporting.extentTest.fail(error);
            ExtentReporting.extentTest.addScreenCaptureFromPath("./screenshots/" + screenshotName);
            String msg = String.format("TEST FAILED - \"%s\". Error: %s", testName, error);
            log.warn(msg);
        } else {
            String msg = String.format("TEST PASSED - \"%s\"", testName);
            ExtentReporting.extentTest.pass(msg);
            log.info(msg);
        }
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        String testName = extensionContext.getDisplayName();
        String msg = String.format("TEST STARTED - \"%s\"", testName);
        log.info(msg);
        ExtentReporting.extentTest = ExtentReporting.extentReports.createTest(testName);
        ExtentReporting.extentTest.log(Status.INFO, msg);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ExtentReporting.extentReports.flush();
    }


}
