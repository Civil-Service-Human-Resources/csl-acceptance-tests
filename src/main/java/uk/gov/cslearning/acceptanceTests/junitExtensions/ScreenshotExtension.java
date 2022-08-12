package uk.gov.cslearning.acceptanceTests.junitExtensions;

import com.aventstack.extentreports.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.cslearning.acceptanceTests.libs.ExtentReport.ExtentReporter;
import uk.gov.cslearning.acceptanceTests.libs.ScreenShotter;

@Slf4j
public class ScreenshotExtension implements AfterAllCallback, AfterTestExecutionCallback, BeforeTestExecutionCallback {

    private ExtentReporter getExtentReporterFromContext(ExtensionContext context) {
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(context);
        return applicationContext.getBean(ExtentReporter.class);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        ExtentReporter extentReporter = getExtentReporterFromContext(extensionContext);
        String testName = extensionContext.getDisplayName();
        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        boolean testFailed = extensionContext.getExecutionException().isPresent();
        if (testFailed) {
            Throwable error = extensionContext.getExecutionException().get();
            extentReporter.failTest(testMethodName, testName, error);
        } else {
            extentReporter.passTest(testName);
        }
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        ExtentReporter extentReporter = getExtentReporterFromContext(extensionContext);
        String testName = extensionContext.getDisplayName();
        extentReporter.createTest(testName);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        getExtentReporterFromContext(extensionContext).flushTests();
    }


}
