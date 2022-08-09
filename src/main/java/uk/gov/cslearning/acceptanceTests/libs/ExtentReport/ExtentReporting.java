package uk.gov.cslearning.acceptanceTests.libs.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.springframework.stereotype.Component;

@Component
public class ExtentReporting {

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public ExtentReporting(ExtentReports extentReports) {
        ExtentReporting.extentReports = extentReports;
    }

}
