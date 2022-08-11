package uk.gov.cslearning.acceptanceTests.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Event;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseManagementOverviewPage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.event.AdminEventCancellationReason;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.event.CancelEventAttendeePage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.event.EventAttendeeOverviewPage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.event.EventOverviewPage;

@Component
public class CourseAdminSteps {

    final CourseManagementOverviewPage courseManagementOverviewPage;

    final EventOverviewPage eventOverviewPage;

    final EventAttendeeOverviewPage eventAttendeeOverviewPage;

    final CancelEventAttendeePage cancelEventAttendeePage;

    public CourseAdminSteps(CourseManagementOverviewPage courseManagementOverviewPage, EventOverviewPage eventOverviewPage, EventAttendeeOverviewPage eventAttendeeOverviewPage, CancelEventAttendeePage cancelEventAttendeePage) {
        this.courseManagementOverviewPage = courseManagementOverviewPage;
        this.eventOverviewPage = eventOverviewPage;
        this.eventAttendeeOverviewPage = eventAttendeeOverviewPage;
        this.cancelEventAttendeePage = cancelEventAttendeePage;
    }

    public void approveBookingRequest(String courseId, String moduleTitle, Event event, String userEmail) {
        courseManagementOverviewPage.navigateTo(courseId);
        courseManagementOverviewPage.viewEventOverview(moduleTitle, event.venue.location, event.getEventDate());
        eventOverviewPage.viewAttendeeOverview(userEmail);
        eventAttendeeOverviewPage.confirmAttendee();
        eventAttendeeOverviewPage.assertAttendeeBooked();
    }

    public void cancelBookingRequest(String courseId, String moduleTitle, Event event, String userEmail) {
        courseManagementOverviewPage.navigateTo(courseId);
        courseManagementOverviewPage.viewEventOverview(moduleTitle, event.venue.location, event.getEventDate());
        eventOverviewPage.viewAttendeeOverview(userEmail);
        eventAttendeeOverviewPage.cancelAttendee();
        cancelEventAttendeePage.confirmCancelAttendee(AdminEventCancellationReason.BOOKING_NOT_PAID);
        //TODO: Assert event spaces are 10/10 again
    }
}
