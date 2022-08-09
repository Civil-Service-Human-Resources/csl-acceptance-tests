package uk.gov.cslearning.acceptanceTests.Steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.EventModule;
import uk.gov.cslearning.acceptanceTests.Utils.SeleniumUtils;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseOverview.*;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.LearningRecordPage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.ELearning.BasicGomoPage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.Event.*;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.VideoModulePage;

import javax.annotation.Nullable;

@Component
public class EventBookingSteps {

    @Autowired
    HomePage homePage;

    @Autowired
    LearningRecordPage learningRecordPage;

    @Autowired
    CourseOverviewPage courseOverviewPage;

    @Autowired
    LinkCourseOverviewPage linkCourseOverviewPage;

    @Autowired
    VideoCourseOverviewPage videoCourseOverviewPage;

    @Autowired
    FileCourseOverviewPage fileCourseOverviewPage;

    @Autowired
    ELearningCourseOverviewPage eLearningCourseOverviewPage;

    @Autowired
    EventCourseOverviewPage eventCourseOverviewPage;

    @Autowired
    EventDateTimeLocationPage eventDateTimeLocationPage;

    @Autowired
    EventPaymentDetailsPage eventPaymentDetailsPage;

    @Autowired
    EventConfirmPaymentDetailsPage eventConfirmPaymentDetailsPage;

    @Autowired
    EventBookingSummaryPage eventBookingSummaryPage;

    @Autowired
    EventBookingRequestSubmittedPage eventBookingRequestSubmittedPage;

    @Autowired
    EventAccessibilityRequirementsPage eventAccessibilityRequirementsPage;

    @Autowired
    EventCancellationPage eventCancellationPage;

    @Autowired
    EventCancellationConfirmationPage eventCancellationConfirmationPage;

    @Autowired
    BasicGomoPage basicGomoPage;

    @Autowired
    VideoModulePage videoModulePage;

    @Autowired
    SeleniumUtils seleniumUtils;

    private void bookEvent(EventModule module, String eventId) {
        eventDateTimeLocationPage.selectEvent(eventId);
        eventAccessibilityRequirementsPage.setAccessibilityRequirements();
        if (module.cost > 0) {
            eventPaymentDetailsPage.setPurchaseOrder("PO123");
            eventConfirmPaymentDetailsPage.confirmPO();
        }
        eventBookingSummaryPage.confirmBooking();
        eventBookingRequestSubmittedPage.assertBookingConfirmed(module.title);
    }

    public void bookEventCourse(String courseId, EventModule module, String eventId) {
        eventCourseOverviewPage.navigateTo(courseId);
        eventCourseOverviewPage.findAvailability();
        bookEvent(module, eventId);
    }

    public void bookEventBlended(String courseId, EventModule module, String eventId) {
        courseOverviewPage.navigateTo(courseId);
        courseOverviewPage.startModule(module.title);
        bookEvent(module, eventId);
    }

    public void cancelBooking(String moduleTitle, @Nullable CancellationReason reason) {
        homePage.navigateTo();
        homePage.cancelBooking(moduleTitle);
        eventCancellationPage.setCancellationReason(reason == null ? CancellationReason.ILLNESS : reason);
        eventCancellationConfirmationPage.assertTitle(moduleTitle);
    }

}
