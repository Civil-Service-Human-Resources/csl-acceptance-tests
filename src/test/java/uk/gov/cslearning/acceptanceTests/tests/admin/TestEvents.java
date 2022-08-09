package uk.gov.cslearning.acceptanceTests.tests.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Event;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.EventModule;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.Data.ModuleCreationService;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.Steps.CourseAdminSteps;
import uk.gov.cslearning.acceptanceTests.Steps.CourseCompletionSteps;
import uk.gov.cslearning.acceptanceTests.Steps.EventBookingSteps;
import uk.gov.cslearning.acceptanceTests.Utils.LearnerRecordService;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.tests.BaseTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TestEvents extends BaseTest {

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
    public void testAdminApprovePaidEvent() {
        CSLUser learner = userManagementService.getUser(UserType.LEARNER);
        LocalDateTime eventDate = today.minus(2, ChronoUnit.HOURS);
        EventModule eventModule = moduleCreationService.eventModule(false, 10, eventDate);
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        Event event = eventModule.events.get(0);
        learnerRecordService.createRequestedBooking(learner, event, today, null);
        learnerRecordService.insertCourseRecord(course, learner, today, "REGISTERED");
        learnerRecordService.insertRequestedBookedModuleRecord(eventModule, learner, event, course.id, today);
        loginUtilityService.switchToType(UserType.ADMIN);
        courseAdminSteps.approveBookingRequest(course.id, eventModule.title, event, learner.email);
        loginUtilityService.switchToType(UserType.LEARNER);
        homePage.assertEventConfirmed(eventModule.title);
    }

    @Test
    public void testAdminCancelPaidEvent() {
        CSLUser learner = userManagementService.getUser(UserType.LEARNER);
        LocalDateTime eventDate = today.minus(2, ChronoUnit.HOURS);
        EventModule eventModule = moduleCreationService.eventModule(false, 10, eventDate);
        Course course = courseManagementService.createCourseWithSetModules(List.of(eventModule));
        courseManagementService.addEvents(course);
        Event event = eventModule.events.get(0);
        learnerRecordService.createRequestedBooking(learner, event, today, null);
        learnerRecordService.insertCourseRecord(course, learner, today, "REGISTERED");
        learnerRecordService.insertRequestedBookedModuleRecord(eventModule, learner, event, course.id, today);
        loginUtilityService.switchToType(UserType.ADMIN);
        courseAdminSteps.cancelBookingRequest(course.id, eventModule.title, event, learner.email);
        loginUtilityService.switchToType(UserType.LEARNER);
        homePage.assertEventCancelled(eventModule.title);
    }


}
