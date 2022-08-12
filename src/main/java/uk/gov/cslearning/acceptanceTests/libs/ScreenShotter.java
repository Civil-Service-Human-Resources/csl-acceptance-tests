package uk.gov.cslearning.acceptanceTests.libs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
public class ScreenShotter {

    private final WebDriver driver;

    private final String screenshotDir;

    public ScreenShotter(String screenshotDir, WebDriver driver) {
        this.screenshotDir = screenshotDir;
        this.driver = driver;
    }

    public String takeScreenshot(String testName) throws IOException {
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = String.format("%s-%s.png", testName, date);
        String screenshotFullDir = String.format("%s/%s", screenshotDir, screenshotName);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotFullDir));
            return screenshotName;
        } catch (IOException e) {
            log.error(String.format("Error when creating screenshot for test method '%s': %s", testName, e.getMessage()));
            throw e;
        }
    }

//    public static String takeScreenshot(String testName) throws IOException {
//        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
//        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String screenshotName = String.format("%s-%s.png", testName, date);
//        String screenshotFullDir = String.format("%s/%s", screenshotDir, screenshotName);
//        try {
//            FileUtils.copyFile(screenshotFile, new File(screenshotFullDir));
//            return screenshotName;
//        } catch (IOException e) {
//            log.error(String.format("Error when creating screenshot for test method '%s': %s", testName, e.getMessage()));
//            throw e;
//        }
//    }
}
