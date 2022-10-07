package uk.gov.cslearning.acceptanceTests.test.learner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Event;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.EventModule;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.data.ModuleCreationService;
import uk.gov.cslearning.acceptanceTests.Models.*;
import uk.gov.cslearning.acceptanceTests.step.CourseAdminSteps;
import uk.gov.cslearning.acceptanceTests.step.CourseCompletionSteps;
import uk.gov.cslearning.acceptanceTests.step.EventBookingSteps;
import uk.gov.cslearning.acceptanceTests.page.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.util.LearnerRecordService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TestEvents extends BaseLearnerTest {

    @Autowired
    ModuleCreationService moduleCreationService;

    @Autowired
    LearnerRecordService learnerRecordService;

    @Autowired
    CourseCompletionSteps courseCompletionSteps;

    @Autowired
    EventBookingSteps eventBookingSteps;

    @Autowired
    CourseAdminSteps courseAdminSteps;

    @Autowired
    HomePage homePage;

    @Test
    @DisplayName("Test that a user can successfully sign up for a free event.")
    public void testSignupForFreeEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 0, learningRecordUtils.getTomorrow());
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        eventBookingSteps.bookEventCourse(course.id, eventModule, eventModule.events.get(0).id);
        seleniumUtils.wait(1000);
        courseCompletionSteps.assertEventIsConfirmedOnHomepage(eventModule.title);
    }

    @Test
    public void testSignupForPaidEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 10, learningRecordUtils.getTomorrow());
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        eventBookingSteps.bookEventCourse(course.id, eventModule, eventModule.events.get(0).id);
        courseCompletionSteps.assertEventIsRequestedOnHomepage(eventModule.title);
    }

    @Test
    public void testCancelFreeEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 0, yesterday.minus(2, ChronoUnit.DAYS));
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        Event event = eventModule.events.get(0);
        learnerRecordService.createConfirmedBookingWithCourseRecord(this.cache.getCurrentUser(), course, event, eventModule, yesterday.minus(3, ChronoUnit.DAYS));
        eventBookingSteps.cancelBooking(eventModule.title, null);
        courseCompletionSteps.assertEventIsCancelledOnHomepage(eventModule.title);
    }

    @Test
    public void testSignupForAndCompleteFreeEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 0, learningRecordUtils.getTomorrow());
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        eventBookingSteps.bookEventCourse(course.id, eventModule, eventModule.events.get(0).id);
        EventModule mod = (EventModule) course.modules.get(0);
        mod.events.get(0).setEventDate(yesterday.minus(2, ChronoUnit.DAYS));
        courseManagementService.createCourse(course);
        courseCompletionSteps.assertEventIsConfirmedOnHomepage(eventModule.title);
        courseCompletionSteps.completeCourseEventOnHomepage(course.title);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }

    @Test
    public void testRebookFreeEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 0, yesterday.plus(3, ChronoUnit.DAYS));
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        Event event = eventModule.events.get(0);
        learnerRecordService.createCancelledBookingWithCourseRecord(this.cache.getCurrentUser(), course, event, eventModule, yesterday.minus(2, ChronoUnit.DAYS));
        eventBookingSteps.bookEventCourse(course.id, eventModule, eventModule.events.get(0).id);
        seleniumUtils.wait(1000);
        courseCompletionSteps.assertEventIsConfirmedOnHomepage(eventModule.title);
    }

    @Test
    public void testDidNotAttendFreeEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 0, yesterday.minus(2, ChronoUnit.DAYS));
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        Event event = eventModule.events.get(0);
        learnerRecordService.createConfirmedBookingWithCourseRecord(this.cache.getCurrentUser(), course, event, eventModule, yesterday.minus(3, ChronoUnit.DAYS));
        courseCompletionSteps.assertEventIsConfirmedOnHomepage(eventModule.title);
        courseCompletionSteps.didNotAttendCourseEventOnHomepage(course.title);
        courseCompletionSteps.assertCourseOnHomepage(course.title);
    }

    @Test
    public void testProgressBlendedCourseWithEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 0, learningRecordUtils.getTomorrow());
        Module videoModule = moduleCreationService.videoModule(false);
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule, videoModule));
        courseManagementService.addEvents(course);
        eventBookingSteps.bookEventBlended(course.id, eventModule, eventModule.events.get(0).id);
        courseCompletionSteps.completeVideoOnBlendedCourse(course.id, videoModule.title);
        courseCompletionSteps.assertEventIsConfirmedOnHomepage(course.title);
    }

    @Test
    public void testProgressBlendedCourseWithPastEvent() {
        EventModule eventModule = moduleCreationService.eventModule(false, 0, yesterday.minus(2, ChronoUnit.DAYS));
        Module videoModule = moduleCreationService.videoModule(false);
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule, videoModule));
        courseManagementService.addEvents(course);
        Event event = eventModule.events.get(0);
        learnerRecordService.createConfirmedBookingWithCourseRecord(this.cache.getCurrentUser(), course, event, eventModule, yesterday.minus(3, ChronoUnit.DAYS));
        courseCompletionSteps.completeVideoOnBlendedCourse(course.id, videoModule.title);
        courseCompletionSteps.completeCourseEventOnHomepage(course.title);
        courseCompletionSteps.assertCourseCompletedOnLearningRecord(course.title, LocalDateTime.now());
    }



    /*
    * TODO: Currently the course doesn't appear as in progress on the homepage if an event has been confirmed - is this OK?
    * */
//    @Test
//    public void testSignupForFreeModuleAndProgress() {
//        EventModule eventModule = moduleCreationService.eventModule(false, 0, learningRecordUtils.getTomorrow());
//        Module videoModule = moduleCreationService.videoModule(false);
//        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule, videoModule));
//        courseManagementService.addEvents(course);
//        EventModule module = (EventModule) course.modules.get(0);
//        eventBookingSteps.bookEventBlended(course.id, module, module.events.get(0).id);
//        courseCompletionSteps.assertEventIsConfirmed(module.title);
//        courseCompletionSteps.startVideoModule(course.id);
//        courseCompletionSteps.assertCourseInProgressOnHomepage(course.title);
//    }


}
