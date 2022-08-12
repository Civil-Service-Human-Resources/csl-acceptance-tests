package uk.gov.cslearning.acceptanceTests.test.admin;

import org.junit.jupiter.api.extension.ExtendWith;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.junitExtensions.AdminSuiteExtension;
import uk.gov.cslearning.acceptanceTests.junitExtensions.GlobalDataExtension;
import uk.gov.cslearning.acceptanceTests.test.BaseTest;


@SeleniumTest
@ExtendWith({AdminSuiteExtension.class, GlobalDataExtension.class})
public class AdminTests extends BaseTest {

}
