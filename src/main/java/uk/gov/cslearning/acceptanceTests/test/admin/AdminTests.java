package uk.gov.cslearning.acceptanceTests.test.admin;

import org.junit.jupiter.api.BeforeEach;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.test.BaseTest;


@SeleniumTest
public class AdminTests extends BaseTest {

    @BeforeEach
    public void beforeEach() {
        loginUtilityService.switchToType(UserType.ADMIN);
    }
}
