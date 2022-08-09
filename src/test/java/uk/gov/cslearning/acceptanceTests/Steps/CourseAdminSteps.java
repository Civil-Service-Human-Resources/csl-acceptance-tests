package uk.gov.cslearning.acceptanceTests.Steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Event;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseManagementOverviewPage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.event.AdminEventCancellationReason;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.event.CancelEventAttendeePage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.event.EventAttendeeOverviewPage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.event.EventOverviewPage;

@Component
public class CourseAdminSteps {

    @Autowired
    CourseManagementOverviewPage courseManagementOverviewPage;

    @Autowired
    EventOverviewPage eventOverviewPage;

    @Autowired
    EventAttendeeOverviewPage eventAttendeeOverviewPage;

    @Autowired
    CancelEventAttendeePage cancelEventAttendeePage;

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
