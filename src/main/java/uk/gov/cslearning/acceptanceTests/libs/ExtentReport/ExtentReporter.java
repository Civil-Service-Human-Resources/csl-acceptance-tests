package uk.gov.cslearning.acceptanceTests.libs.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.libs.ScreenShotter;

@Component
@Slf4j
public class ExtentReporter {

    final ScreenShotter screenShotter;
    final ExtentReports extentReports;

    private ExtentTest extentTest;

    public ExtentReporter(ExtentReports extentReports, ScreenShotter screenShotter) {
        this.extentReports = extentReports;
        this.screenShotter = screenShotter;
    }

    public void logTest(String message) {
        extentTest.info(message);
    }

    public void flushTests() {
        log.debug("Flushing tests and creating report");
        extentReports.flush();
    }

    public void failTest(String testMethodName, String testDisplayName, Throwable error) throws Exception {
        String msg = String.format("TEST FAILED - \"%s\". Error: %s", testDisplayName, error);
        String screenshotName = screenShotter.takeScreenshot(testMethodName);
        extentTest.fail(error);
        extentTest.addScreenCaptureFromPath("./screenshots/" + screenshotName);
        log.error(msg);
    }

    public void passTest(String testName) {
        String msg = String.format("TEST PASSED - \"%s\"", testName);
        extentTest.pass(msg);
        log.info(msg);
    }

    public void createTest(String testName) {
        String msg = String.format("TEST STARTED - \"%s\"", testName);
        log.info(msg);
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO, msg);
    }

}
