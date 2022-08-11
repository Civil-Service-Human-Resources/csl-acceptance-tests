package uk.gov.cslearning.acceptanceTests.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.EventModule;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CourseOverview.*;
import uk.gov.cslearning.acceptanceTests.page.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.page.CslUi.LearningRecordPage;
import uk.gov.cslearning.acceptanceTests.page.CslUi.Modules.ELearning.BasicGomoPage;
import uk.gov.cslearning.acceptanceTests.page.CslUi.Modules.Event.*;
import uk.gov.cslearning.acceptanceTests.page.CslUi.Modules.VideoModulePage;
import uk.gov.cslearning.acceptanceTests.util.SeleniumUtils;

import javax.annotation.Nullable;

@Component
public class EventBookingSteps {

    final HomePage homePage;

    final LearningRecordPage learningRecordPage;

    final CourseOverviewPage courseOverviewPage;

    final LinkCourseOverviewPage linkCourseOverviewPage;

    final VideoCourseOverviewPage videoCourseOverviewPage;

    final FileCourseOverviewPage fileCourseOverviewPage;

    final ELearningCourseOverviewPage eLearningCourseOverviewPage;

    final EventCourseOverviewPage eventCourseOverviewPage;

    final EventDateTimeLocationPage eventDateTimeLocationPage;

    final EventPaymentDetailsPage eventPaymentDetailsPage;

    final EventConfirmPaymentDetailsPage eventConfirmPaymentDetailsPage;

    final EventBookingSummaryPage eventBookingSummaryPage;

    final EventBookingRequestSubmittedPage eventBookingRequestSubmittedPage;

    final EventAccessibilityRequirementsPage eventAccessibilityRequirementsPage;

    final EventCancellationPage eventCancellationPage;

    final EventCancellationConfirmationPage eventCancellationConfirmationPage;

    final BasicGomoPage basicGomoPage;

    final VideoModulePage videoModulePage;

    final SeleniumUtils seleniumUtils;

    public EventBookingSteps(LinkCourseOverviewPage linkCourseOverviewPage, HomePage homePage, EventAccessibilityRequirementsPage eventAccessibilityRequirementsPage, LearningRecordPage learningRecordPage, BasicGomoPage basicGomoPage, CourseOverviewPage courseOverviewPage, VideoCourseOverviewPage videoCourseOverviewPage, EventCancellationConfirmationPage eventCancellationConfirmationPage, EventCancellationPage eventCancellationPage, FileCourseOverviewPage fileCourseOverviewPage, EventBookingSummaryPage eventBookingSummaryPage, ELearningCourseOverviewPage eLearningCourseOverviewPage, VideoModulePage videoModulePage, EventCourseOverviewPage eventCourseOverviewPage, EventDateTimeLocationPage eventDateTimeLocationPage, SeleniumUtils seleniumUtils, EventBookingRequestSubmittedPage eventBookingRequestSubmittedPage, EventPaymentDetailsPage eventPaymentDetailsPage, EventConfirmPaymentDetailsPage eventConfirmPaymentDetailsPage) {
        this.linkCourseOverviewPage = linkCourseOverviewPage;
        this.homePage = homePage;
        this.eventAccessibilityRequirementsPage = eventAccessibilityRequirementsPage;
        this.learningRecordPage = learningRecordPage;
        this.basicGomoPage = basicGomoPage;
        this.courseOverviewPage = courseOverviewPage;
        this.videoCourseOverviewPage = videoCourseOverviewPage;
        this.eventCancellationConfirmationPage = eventCancellationConfirmationPage;
        this.eventCancellationPage = eventCancellationPage;
        this.fileCourseOverviewPage = fileCourseOverviewPage;
        this.eventBookingSummaryPage = eventBookingSummaryPage;
        this.eLearningCourseOverviewPage = eLearningCourseOverviewPage;
        this.videoModulePage = videoModulePage;
        this.eventCourseOverviewPage = eventCourseOverviewPage;
        this.eventDateTimeLocationPage = eventDateTimeLocationPage;
        this.seleniumUtils = seleniumUtils;
        this.eventBookingRequestSubmittedPage = eventBookingRequestSubmittedPage;
        this.eventPaymentDetailsPage = eventPaymentDetailsPage;
        this.eventConfirmPaymentDetailsPage = eventConfirmPaymentDetailsPage;
    }

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
