package uk.gov.cslearning.acceptanceTests.libs.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtentConfiguration {

    @Value("${report.dir}")
    public String reportDir;

    @Bean
    public ExtentReports getExtentReports() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportDir);
        extent.attachReporter(spark);
        return extent;
    }

}
