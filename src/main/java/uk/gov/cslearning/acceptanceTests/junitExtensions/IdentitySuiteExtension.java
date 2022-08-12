package uk.gov.cslearning.acceptanceTests.junitExtensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.util.LoginUtilityService;

@Slf4j
public class IdentitySuiteExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        log.info("Signing out for identity tests");
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(extensionContext);
        applicationContext.getBean(LoginUtilityService.class).signOut();
    }
}
