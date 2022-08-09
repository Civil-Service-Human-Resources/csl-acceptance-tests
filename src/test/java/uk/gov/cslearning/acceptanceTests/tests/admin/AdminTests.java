package uk.gov.cslearning.acceptanceTests.tests.admin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.tests.BaseTest;


@SeleniumTest
public class AdminTests extends BaseTest {

    @BeforeEach
    public void beforeEach() {
        loginUtilityService.switchToType(UserType.ADMIN);
    }
}
