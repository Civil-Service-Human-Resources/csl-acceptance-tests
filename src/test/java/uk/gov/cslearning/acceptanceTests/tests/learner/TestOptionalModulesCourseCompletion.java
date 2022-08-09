package uk.gov.cslearning.acceptanceTests.tests.learner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.ELearningModule;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.FileModule;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.Data.ModuleCreationService;
import uk.gov.cslearning.acceptanceTests.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.Steps.CourseCompletionSteps;
import uk.gov.cslearning.acceptanceTests.Utils.LoginUtilityService;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseCompletePage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseOverview.CourseOverviewPage;

import java.time.LocalDateTime;
import java.util.List;


@SeleniumTest
@Slf4j
public class TestOptionalModulesCourseCompletion extends BaseLearnerTest {

    @Autowired
    ModuleCreationService moduleCreationService;

    @Autowired
    CourseOverviewPage courseOverviewPage;

    @Autowired
    CourseCompletePage courseCompletePage;

    @Autowired
    LoginUtilityService loginUtilityService;

    @Autowired
    CourseCompletionSteps courseCompletionSteps;

    @DisplayName("Test that a blended course completes when all required modules have been completed")
    @Test
    public void testCourseCompleteWhenAllRequiredModulesAreComplete() {
        Module requiredLinkModule = moduleCreationService.linkModule(false);
        Module optionalLinkModule = moduleCreationService.linkModule(true);
        Course course = courseManagementService.createCourseWithSetModules(List.of(new Module[]{requiredLinkModule, optionalLinkModule}));
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, requiredLinkModule.title);
        courseCompletionSteps.assertModuleCompletedOnCourseOverview(course.id, requiredLinkModule.title);
        seleniumUtils.wait(1000);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, today);
    }

    @DisplayName("Test that an optional blended course completes when all optional modules have been completed")
    @Test
    public void testOptionalCourseCompleteWhenAllOptionalModulesAreComplete() {
        Module optionalLinkModule1 = moduleCreationService.linkModule(true);
        Module optionalLinkModule2 = moduleCreationService.linkModule(true);
        Course course = courseManagementService.createCourseWithSetModules(List.of(new Module[]{optionalLinkModule1, optionalLinkModule2}));
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, optionalLinkModule1.title);
        courseCompletionSteps.assertModuleCompletedOnCourseOverview(course.id, optionalLinkModule1.title);
        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, optionalLinkModule2.title);
        courseCompletionSteps.assertModuleCompletedOnCourseOverview(course.id, optionalLinkModule2.title);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, today);
    }

}
