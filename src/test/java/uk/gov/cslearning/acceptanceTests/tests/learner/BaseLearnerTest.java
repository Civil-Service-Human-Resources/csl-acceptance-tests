package uk.gov.cslearning.acceptanceTests.tests.learner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.Utils.*;
import uk.gov.cslearning.acceptanceTests.tests.BaseTest;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SeleniumTest
public class BaseLearnerTest extends BaseTest {

    @BeforeEach
    public void beforeEach() {
        loginUtilityService.switchToType(UserType.LEARNER);
    }

//    @AfterAll
//    public void afterAll(){
//        loginUtilityService.signOut();
//    }

}
