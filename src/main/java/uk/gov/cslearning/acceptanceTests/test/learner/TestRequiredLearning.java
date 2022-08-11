package uk.gov.cslearning.acceptanceTests.test.learner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.data.ModuleCreationService;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.step.CourseCompletionSteps;
import uk.gov.cslearning.acceptanceTests.page.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.util.LearnerRecordService;
import uk.gov.cslearning.acceptanceTests.util.LearningRecordUtils;

import java.time.LocalDateTime;

public class TestRequiredLearning extends BaseLearnerTest {

    @Autowired
    ModuleCreationService moduleCreationService;

    @Autowired
    LearnerRecordService learnerRecordService;

    @Autowired
    LearningRecordUtils learningRecordUtils;

    @Autowired
    CourseCompletionSteps courseCompletionSteps;

    @Autowired
    HomePage homePage;



    /*
    * Test that a course comes back into the required learning section of the
    * homepage after it has already been completed and a module is progressed
    * in the new learning period.
    * */
    @Test
    @DisplayName("Progress a required course which has already been completed")
    public void testCourseAlreadyCompletedAndProgressed() {
        CSLUser user = this.cache.getCurrentUser();
        LocalDateTime yesterday = learningRecordUtils.getYesterday();
        Course course = courseManagementService.createRequiredBlendedCourseForUser(user, yesterday);
        learnerRecordService.addCompletedRequiredCourseRecord(course, user, yesterday);
        learnerRecordService.addCompletedRequiredModuleRecords(course.modules, user, yesterday, course.id);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, yesterday);
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, course.modules.get(1).title);
        seleniumUtils.wait(1000);
        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
    }

    @Test
    @DisplayName("Complete a required course which has already been completed")
    public void testCourseAlreadyCompletedAndCompleted() {
        CSLUser user = this.cache.getCurrentUser();
        LocalDateTime yesterday = learningRecordUtils.getYesterday();
        Course course = courseManagementService.createRequiredBlendedCourseForUser(user, yesterday);
        learnerRecordService.addCompletedRequiredCourseRecord(course, user, yesterday);
        learnerRecordService.addCompletedRequiredModuleRecords(course.modules, user, yesterday, course.id);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, yesterday);
        courseCompletionSteps.completeVideoOnBlendedCourse(course.id, course.modules.get(1).title);
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, course.modules.get(0).title);
        seleniumUtils.wait(1000);
        courseCompletionSteps.assertCourseNotOnHomepage(course.title);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }
}
