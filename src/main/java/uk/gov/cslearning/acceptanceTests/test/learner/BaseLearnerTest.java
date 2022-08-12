package uk.gov.cslearning.acceptanceTests.test.learner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.test.BaseTest;

@SeleniumTest
public class BaseLearnerTest extends BaseTest {

    @BeforeAll
    public void beforeEach() {
        loginUtilityService.switchToType(UserType.LEARNER);
    }

}
