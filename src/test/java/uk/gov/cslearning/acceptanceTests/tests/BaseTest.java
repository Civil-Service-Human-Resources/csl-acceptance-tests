package uk.gov.cslearning.acceptanceTests.tests;

import lombok.Getter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import uk.gov.cslearning.acceptanceTests.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.Utils.*;
import uk.gov.cslearning.acceptanceTests.annotation.LazyAutowired;
import uk.gov.cslearning.acceptanceTests.extensions.ScreenshotExtension;

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

    @LazyAutowired
    public ApplicationContext applicationContext;

//    @AfterEach
//    public void teardown() {
//        this.applicationContext
//                .getBean(WebDriver.class)
//                .quit();
//    }
}
