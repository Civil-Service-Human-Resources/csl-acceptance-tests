package uk.gov.cslearning.acceptanceTests.test.learner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.junitExtensions.GlobalDataExtension;
import uk.gov.cslearning.acceptanceTests.junitExtensions.LearnerSuiteExtension;
import uk.gov.cslearning.acceptanceTests.test.BaseTest;

@ExtendWith({LearnerSuiteExtension.class, GlobalDataExtension.class})
public class BaseLearnerTest extends BaseTest {

}
