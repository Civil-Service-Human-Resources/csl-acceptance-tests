package uk.gov.cslearning.acceptanceTests.Utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Event;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model.Booking;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.LearnerRecordDBClient;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model.CourseRecord;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model.ModuleRecord;
import uk.gov.cslearning.acceptanceTests.Models.*;
import uk.gov.cslearning.acceptanceTests.Models.builder.BookingBuilder;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearnerRecordService {

    @Autowired
    LearnerRecordDBClient dbClient;

    private Long getLearnerId(CSLUser user) {
        Long id = dbClient.getLearnerId(user.uid);
        if (id == null) {
            dbClient.insertLearner(user);
            id = dbClient.getLearnerId(user.uid);
        }
        return id;
    }

    public Long getEventId(String eventUid) {
        return dbClient.getEventId(eventUid);
    }

    public BookingBuilder getGenericPartialBooking(CSLUser user, Event event, LocalDateTime bookingTime) {
        Long learnerId = getLearnerId(user);
        Long eventId = getEventId(event.id);
        return new BookingBuilder(learnerId, eventId, bookingTime, RandomStringUtils.randomAlphanumeric(5));
    }

    public void createConfirmedBooking(CSLUser user, Event event, LocalDateTime bookingTime, @Nullable String poNumber) {
        LocalDateTime confirmedDate = bookingTime.plus(1, ChronoUnit.DAYS);
        Booking b = getGenericPartialBooking(user, event, bookingTime)
                .setPoNumber(poNumber)
                .setConfirmed(confirmedDate)
                .build();
        dbClient.insertBooking(b);
    }

    public void createCancelledBooking(CSLUser user, Event event, LocalDateTime bookingTime, @Nullable String poNumber) {
        LocalDateTime cancelledDate = bookingTime.plus(1, ChronoUnit.DAYS);
        Booking b = getGenericPartialBooking(user, event, bookingTime)
                .setPoNumber(poNumber)
                .setCancelled("PRIORITIES", cancelledDate)
                .build();
        dbClient.insertBooking(b);
    }

    public void createRequestedBooking(CSLUser user, Event event, LocalDateTime bookingTime, @Nullable String poNumber) {
        Booking b = getGenericPartialBooking(user, event, bookingTime)
                .setPoNumber(poNumber)
                .setStatus("REQUESTED")
                .build();
        dbClient.insertBooking(b);
    }

    public void addCompletedRequiredCourseRecord(Course course, CSLUser user, LocalDateTime lastUpdated) {
        CourseRecord cr = CourseRecord.fromCourse(course, user.uid, "COMPLETED", 1, lastUpdated);
        dbClient.insertCourseRecords(List.of(new CourseRecord[]{cr}));
    }

    public void insertCourseRecord(Course course, CSLUser user, LocalDateTime lastUpdated, String state) {
        CourseRecord cr = CourseRecord.fromCourse(course, user.uid, state, 1, lastUpdated);
        dbClient.insertCourseRecords(List.of(new CourseRecord[]{cr}));
    }

    public void addCompletedRequiredModuleRecords(List<Module> modules, CSLUser user, LocalDateTime completedDate, String courseId) {
        LocalDateTime createdDate = completedDate.minus(1, ChronoUnit.MONTHS);
        List<ModuleRecord> moduleRecords = modules.stream().map(m -> ModuleRecord.fromModule(m, null, "COMPLETED",completedDate,createdDate,completedDate,
                courseId, user.uid, null, null)).collect(Collectors.toList());
        dbClient.insertModuleRecords(moduleRecords);
    }

    public void insertConfirmedBookedModuleRecord(Module module, CSLUser user, Event event, String courseId, LocalDateTime createdDate) {
        LocalDateTime eventDate = event.getEventDate();
        ModuleRecord mr = ModuleRecord.fromModule(module, event.id, "APPROVED", null, createdDate,
                createdDate, courseId, user.uid, null, eventDate);
        dbClient.insertModuleRecord(mr);
    }

    public void insertRequestedBookedModuleRecord(Module module, CSLUser user, Event event, String courseId, LocalDateTime createdDate) {
        LocalDateTime eventDate = event.getEventDate();
        ModuleRecord mr = ModuleRecord.fromModule(module, event.id, "REGISTERED", null, createdDate,
                createdDate, courseId, user.uid, null, eventDate);
        dbClient.insertModuleRecord(mr);
    }

}
