package uk.gov.cslearning.acceptanceTests.extensions;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import uk.gov.cslearning.acceptanceTests.libs.ExtentReport.ExtentReporting;
import uk.gov.cslearning.acceptanceTests.libs.ScreenShotter;
import uk.gov.cslearning.acceptanceTests.libs.webDriver.StaticWebDriverAccess;

public class ScreenshotExtension implements AfterAllCallback, AfterTestExecutionCallback, BeforeTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        String testName = extensionContext.getDisplayName();
        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        boolean testFailed = extensionContext.getExecutionException().isPresent();
        if (testFailed) {
            String screenshotName = ScreenShotter.takeScreenshot(testMethodName);
            ExtentReporting.extentTest.fail(extensionContext.getExecutionException().get());
            ExtentReporting.extentTest.addScreenCaptureFromPath("./screenshots/" + screenshotName);
        } else {
            ExtentReporting.extentTest.pass(testName + " - passed");
        }
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        String testName = extensionContext.getDisplayName();
        ExtentReporting.extentTest = ExtentReporting.extentReports.createTest(testName);
        ExtentReporting.extentTest.log(Status.INFO, testName + " - started");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ExtentReporting.extentReports.flush();
    }


}
