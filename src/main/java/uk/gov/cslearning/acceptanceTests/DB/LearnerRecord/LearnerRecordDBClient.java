package uk.gov.cslearning.acceptanceTests.DB.LearnerRecord;

import com.dieselpoint.norm.Database;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.DB.DbClient;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model.*;
import uk.gov.cslearning.acceptanceTests.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class LearnerRecordDBClient extends DbClient {

    private final Database dbConnection;


    public LearnerRecordDBClient(@Qualifier("LearnerRecordDB") Database dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Database getDb() {
        return dbConnection;
    }

    public void insertModuleRecords(List<ModuleRecord> moduleRecords) {
        for (ModuleRecord mr : moduleRecords) {
            dbConnection.table("module_record").insert(mr);
        }
    }

    public void insertModuleRecord(ModuleRecord moduleRecord) {
        dbConnection.insert(moduleRecord);
    }

    public void insertCourseRecords(List<CourseRecord> courseRecords) {
        for (CourseRecord cr : courseRecords) {
            dbConnection.table("module_record").insert(cr);
        }
    }

    public void insertEvent(String courseId, String moduleId, String eventUid) {
        String path = String.format("/courses/%s/modules/%s/events/%s", courseId, moduleId, eventUid);
        Event event = new Event(eventUid, path, "ACTIVE", null);
        dbConnection.table("event").insert(event);
    }

    public Long getLearnerId(String uid) {
        return dbConnection.sql("select id from learner where uid = ?", uid).first(Long.class);
    }

    public Long getEventId(String eventUid) {
        return dbConnection.sql("select id from event where uid = ?", eventUid).first(Long.class);
    }

    public void insertLearner(CSLUser user) {
        Learner learner = new Learner(user.uid, user.email);
        dbConnection.table("learner").insert(learner);
    }
    public void insertBooking(Booking booking) {
        dbConnection.insert(booking);
    }

    public void deleteAllAcceptanceTestEvents() {
        dbConnection.table("event").where("uid like ?", "ACCEPTANCE%").delete();
    }

    public void deleteAllAcceptanceTestCourseRecords() {
        dbConnection.table("course_record").where("course_id like ?", "ACCEPTANCE%").delete();
    }

    public void deleteAllAcceptanceTestModuleRecords() {
        dbConnection.table("module_record").where("course_id like ?", "ACCEPTANCE%").delete();
    }

    public void deleteAllAcceptanceTestRecords() {
        deleteAllAcceptanceTestModuleRecords();
        deleteAllAcceptanceTestCourseRecords();
        deleteAllAcceptanceTestEvents();
    }

}
