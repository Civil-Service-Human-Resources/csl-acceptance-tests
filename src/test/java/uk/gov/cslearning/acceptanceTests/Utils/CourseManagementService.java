package uk.gov.cslearning.acceptanceTests.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.ElasticsearchClient;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.*;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model.CourseRecord;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model.ModuleRecord;
import uk.gov.cslearning.acceptanceTests.Data.AudienceCreationService;
import uk.gov.cslearning.acceptanceTests.Models.*;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.LearnerRecordDBClient;
import uk.gov.cslearning.acceptanceTests.DB.LearningLockerClient;
import uk.gov.cslearning.acceptanceTests.Data.CourseData;
import uk.gov.cslearning.acceptanceTests.Data.ModuleCreationService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CourseManagementService {

    @Autowired
    ModuleCreationService moduleCreationService;

    @Autowired
    AudienceCreationService audienceCreationService;

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    ElasticsearchClient esClient;

    @Autowired
    LearnerRecordDBClient learnerRecordDBClient;

    @Autowired
    LearningLockerClient learningLockerClient;

    @Autowired
    Cache cache;

    public Course createCourse(Course course) {
        esClient.createCourse(course);
        return course;
    }

    public Course createGenericCourse() {
        Course course = CourseData.genericCourse();
        return createCourse(course);
    }

    /*
    * Creates a generic blended course which has a required by date for the user's department.
    * Has a link module[0] and a video module[1]
    * */
    public Course createRequiredBlendedCourseForUser(CSLUser user, LocalDateTime requiredBy) {
        Course course = CourseData.genericCourse();
        Module linkModule = moduleCreationService.linkModule(false);
        Module videoModule = moduleCreationService.videoModule(false);
        Audience audience = audienceCreationService.generateRequiredAudienceForUser(user, requiredBy);
        course.modules.addAll(List.of(new Module[]{linkModule, videoModule}));
        course.audiences.add(audience);
        return createCourse(course);
    }

    public Course createCourseForUser(CSLUser user, List<Module> modules) {
        Course course = CourseData.genericCourse();
        course.modules = modules;
        Audience audience = audienceCreationService.generateAudienceForUser(user);
        course.audiences.add(audience);
        return createCourse(course);
    }

    public Course createRequiredCourseForUser(CSLUser user, List<Module> modules, LocalDateTime requiredBy) {
        Course course = CourseData.genericCourse();
        course.modules = modules;
        Audience audience = audienceCreationService.generateRequiredAudienceForUser(user, requiredBy);
        course.audiences.add(audience);
        return createCourse(course);
    }

    public Course createCourseForUser(CSLUser user) {
        Course course = CourseData.genericCourse();
        Audience audience = audienceCreationService.generateAudienceForUser(user);
        course.audiences.add(audience);
        return createCourse(course);
    }

    public Course createCourseWithSetModules(List<Module> modules) {
        Course course = CourseData.genericCourse();
        course.modules = modules;
        return createCourse(course);
    }

    public Course createCourseWithOneModule(Module module) {
        List<Module> modules = List.of(module);
        return createCourseWithSetModules(modules);
    }

    public Course createCourseWithRequiredLinkModules(int moduleCount) {
        Course course = CourseData.genericCourse();
        for (int i = 0; i < moduleCount; i++) {
            Module module = moduleCreationService.linkModule(false);
            course.modules.add(module);
        }
        return createCourse(course);
    }

    public void addEvents(Course course) {
        for (Module m : course.modules) {
            if (m.type.equals("face-to-face")) {
                EventModule em = (EventModule)m;
                for (Event e : em.events) {
                    learnerRecordDBClient.insertEvent(course.id, em.id, e.id);
                }
            }
        }
    }

    public void cleanUpCourses() {
        log.info("Tearing down all acceptance test records from MySQL");
        learnerRecordDBClient.deleteAllAcceptanceTestRecords();
        log.info("Tearing down all acceptance test courses from ElasticSearch");
        esClient.deleteAcceptanceCourses();
        log.info("Tearing down all acceptance test learning locker statements");
        learningLockerClient.deleteAllAcceptanceTestStatements();
    }
}
