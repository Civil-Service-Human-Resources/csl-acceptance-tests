package uk.gov.cslearning.acceptanceTests.test.identity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.test.BaseTest;

@SeleniumTest
public class IdentityTests extends BaseTest {

    @BeforeAll
    public void beforeAll() {
        loginUtilityService.trySignOut();
    }
}
