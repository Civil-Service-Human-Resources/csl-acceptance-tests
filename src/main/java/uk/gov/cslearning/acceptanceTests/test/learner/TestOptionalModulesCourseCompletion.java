package uk.gov.cslearning.acceptanceTests.test.learner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.data.ModuleCreationService;
import uk.gov.cslearning.acceptanceTests.annotation.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.step.CourseCompletionSteps;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CourseCompletePage;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CourseOverview.CourseOverviewPage;
import uk.gov.cslearning.acceptanceTests.util.LoginUtilityService;

import java.util.List;

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
