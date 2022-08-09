package uk.gov.cslearning.acceptanceTests.tests.learner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.ELearningModule;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.FileModule;
import uk.gov.cslearning.acceptanceTests.Data.ModuleCreationService;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.SeleniumTest;
import uk.gov.cslearning.acceptanceTests.Steps.CourseCompletionSteps;
import uk.gov.cslearning.acceptanceTests.Utils.*;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseCompletePage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseOverview.*;

import java.time.LocalDateTime;
import java.util.List;


@SeleniumTest
@Slf4j
public class TestCourseCompletion extends BaseLearnerTest {

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

    @Test
    public void testCompleteLinkCourse() {
        Course course = courseManagementService.createCourseWithRequiredLinkModules(1);
        courseCompletionSteps.completeLinkModule(course.id);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

    @Test
    public void testCompleteFileCourse() {
        FileModule pdfModule = moduleCreationService.fileModule(false);
        Course course = courseManagementService.createCourseWithOneModule(pdfModule);
        courseCompletionSteps.completeFileModule(course.id, pdfModule.file.name);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

    @Test
    public void testProgressVideoModule() {
        Module videoModule = moduleCreationService.videoModule(false);
        Course course = courseManagementService.createCourseWithOneModule(videoModule);
        courseCompletionSteps.startVideoModule(course.id);
        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
    }

    @Test
    public void testCompleteVideoCourse() {
        Module videoModule = moduleCreationService.videoModule(false);
        Course course = courseManagementService.createCourseWithOneModule(videoModule);
        courseCompletionSteps.completeVideoModule(course.id);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

    @Test
    public void testProgressElearningModule() {
        Module elearningModule = moduleCreationService.eLearningModule(false);
        Course course = courseManagementService.createCourseWithOneModule(elearningModule);
        courseCompletionSteps.startELearningModule(course.id);
        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
    }

    @Test
    public void testCompleteElearningCourse() {
        ELearningModule elearningModule = moduleCreationService.eLearningModule(false);
        Course course = courseManagementService.createCourseWithOneModule(elearningModule);
        courseCompletionSteps.completeELearningModule(course.id);
        courseCompletePage.navigateTo(course.id, elearningModule.id);
        courseCompletePage.assertCourseTitleAndModuleTitle(course.title, course.title);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

    @Test
    public void testCourseInProgressThenComplete() {
        Module linkModule = moduleCreationService.linkModule(false);
        Module linkModule2 = moduleCreationService.linkModule(false);
        Course course = courseManagementService.createCourseWithSetModules(List.of(new Module[]{linkModule, linkModule2}));
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, linkModule.title);
        courseCompletionSteps.assertModuleCompletedOnCourseOverview(course.id, linkModule.title);
        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, linkModule2.title);
        seleniumUtils.wait(1000);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, today);
    }

    @Test
    public void testBlendedCourseInProgress() {
        Module videoModule = moduleCreationService.videoModule(false);
        FileModule fileModule = moduleCreationService.fileModule(false);
        Course course = courseManagementService.createCourseWithSetModules(List.of(new Module[]{videoModule, fileModule}));
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, videoModule.title);
        courseCompletionSteps.assertModuleInProgressOnCourseOverview(course.id, videoModule.title);
        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
    }

    @Test
    public void testBlendedCourseComplete() {
        Module videoModule = moduleCreationService.videoModule(false);
        FileModule fileModule = moduleCreationService.fileModule(false);
        Course course = courseManagementService.createCourseWithSetModules(List.of(new Module[]{videoModule, fileModule}));
        courseCompletionSteps.completeVideoOnBlendedCourse(course.id, videoModule.title);
        seleniumUtils.wait(4000);
        courseCompletionSteps.assertModuleCompletedOnCourseOverview(course.id, videoModule.title);
        courseCompletionSteps.doModuleOnBlendedCourse(course.id, fileModule.title);
        seleniumUtils.wait(2000);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

}
