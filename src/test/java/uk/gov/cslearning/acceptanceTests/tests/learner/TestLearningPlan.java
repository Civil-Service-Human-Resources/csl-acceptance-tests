package uk.gov.cslearning.acceptanceTests.tests.learner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.Data.ModuleCreationService;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.Steps.CourseCompletionSteps;
import uk.gov.cslearning.acceptanceTests.Steps.LearningPlanSteps;
import uk.gov.cslearning.acceptanceTests.Utils.CourseManagementService;
import uk.gov.cslearning.acceptanceTests.tests.BaseTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class TestLearningPlan extends BaseLearnerTest {

    @Autowired
    LearningPlanSteps learningPlanSteps;

    @Autowired
    CourseCompletionSteps courseCompletionSteps;

    @Autowired
    CourseManagementService courseManagementService;

    @Autowired
    ModuleCreationService moduleCreationService;

    @Test
    public void testAddToLearningPlan() {
        CSLUser user = this.cache.getCurrentUser();
        Course course = courseManagementService.createCourseForUser(user, List.of(moduleCreationService.linkModule(false)));
        learningPlanSteps.addToLearningPlan(course.title);
    }

    @Test
    public void testAddThenRemoveFromLearningPlan() {
        CSLUser user = this.cache.getCurrentUser();
        Course course = courseManagementService.createCourseForUser(user, List.of(moduleCreationService.linkModule(false)));
        seleniumUtils.wait(1000);
        learningPlanSteps.addToLearningPlan(course.title);
        learningPlanSteps.removeFromLearningPlan(course.title);
    }

    @Test
    public void testRemoveFromSuggestions() {
        CSLUser user = this.cache.getCurrentUser();
        Course course = courseManagementService.createCourseForUser(user);
        seleniumUtils.wait(1000);
        learningPlanSteps.removeFromSuggestions(course.title);
    }

    @Test
    public void testRemoveFromSuggestionsAndComplete() {
        CSLUser user = this.cache.getCurrentUser();
        Module linkModule = moduleCreationService.linkModule(false);
        Course course = courseManagementService.createCourseForUser(user, List.of(new Module[]{linkModule}));
        seleniumUtils.wait(1000);
        learningPlanSteps.removeFromSuggestions(course.title);
        courseCompletionSteps.completeLinkModule(course.id);
        seleniumUtils.wait(1000);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

    /*
    * TODO: Figure out if this is functionality that should work. At the moment, removing
    *  a course from suggestions causes it to never appear in the learning plan/homepage.
    * */
//    @Test
//    public void testProgressCourseAndRemoveFromPlanAndProgress() {
//        CSLUser user = this.cache.getCurrentUser();
//        Module linkModule = moduleCreationService.linkModule(false);
//        Module videoModule = moduleCreationService.videoModule(false);
//        Course course = courseManagementService.createCourseForUser(user, List.of(new Module[]{linkModule, videoModule}));
//        courseCompletionSteps.doModuleOnBlendedCourse(course.id, linkModule.title);
//        // Tests run a little too fast for Learning Locker to keep up with... so wait for a bit
//        seleniumUtils.wait(1000);
//        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
//        learningPlanSteps.removeFromLearningPlan(course.title);
//        courseCompletionSteps.doModuleOnBlendedCourse(course.id, videoModule.title);
//        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
//    }

    @Test
    public void testProgressCourseAndRemoveFromLearningPlanAndComplete() {
        CSLUser user = this.cache.getCurrentUser();
        Module linkModule = moduleCreationService.linkModule(false);
        Module videoModule = moduleCreationService.videoModule(false);
        Course course = courseManagementService.createCourseForUser(user, List.of(new Module[]{linkModule, videoModule}));
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, linkModule.title);
        // Tests run a little too fast for Learning Locker to keep up with... so wait for a bit
        seleniumUtils.wait(1000);
        learningPlanSteps.removeFromLearningPlan(course.title);
        courseCompletionSteps.completeVideoOnBlendedCourse(course.id, videoModule.title);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

}
