package uk.gov.cslearning.acceptanceTests.test;

import com.aventstack.extentreports.Status;
import lombok.Getter;
import org.junit.jupiter.api.AfterAll;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.libs.ExtentReport.ExtentReporting;
import uk.gov.cslearning.acceptanceTests.util.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SeleniumTest
@Getter
public class BaseTest {

    @Autowired
    protected Cache cache;

    @Autowired
    protected LoginUtilityService loginUtilityService;

    @Autowired
    protected SeleniumUtils seleniumUtils;

    @Autowired
    protected CourseManagementService courseManagementService;

    @Autowired
    protected UserManagementService userManagementService;

    @Autowired
    protected LearningRecordUtils learningRecordUtils;

    protected LocalDateTime today;
    protected LocalDateTime yesterday;
    protected LocalDateTime tomorrow;

    @PostConstruct
    public void Init() {
        today = learningRecordUtils.getToday();
        yesterday = learningRecordUtils.getYesterday();
        tomorrow = learningRecordUtils.getTomorrow();
    }

    @AfterAll
    public void after() {
        courseManagementService.cleanUpCourses();
        userManagementService.teardownTokens();
    }

    protected void testLog(String message) {
        ExtentReporting.extentTest.log(Status.INFO, message);
    }
}
