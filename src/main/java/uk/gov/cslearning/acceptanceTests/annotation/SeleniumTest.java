package uk.gov.cslearning.acceptanceTests.annotation;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.cslearning.acceptanceTests.AcceptanceTestsApplication;
import uk.gov.cslearning.acceptanceTests.junitExtensions.GlobalDataExtension;
import uk.gov.cslearning.acceptanceTests.junitExtensions.ScreenshotExtension;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AcceptanceTestsApplication.class)
@ExtendWith(ScreenshotExtension.class)
public @interface SeleniumTest {
}
