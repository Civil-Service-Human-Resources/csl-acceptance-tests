package uk.gov.cslearning.acceptanceTests.tests.learner;

import org.junit.jupiter.api.BeforeEach;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.annotations.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.tests.BaseTest;

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
