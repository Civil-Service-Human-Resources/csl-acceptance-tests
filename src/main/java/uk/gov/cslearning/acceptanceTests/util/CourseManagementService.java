package uk.gov.cslearning.acceptanceTests.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.ElasticsearchClient;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.*;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.data.AudienceCreationService;
import uk.gov.cslearning.acceptanceTests.Models.*;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.LearnerRecordDBClient;
import uk.gov.cslearning.acceptanceTests.DB.LearningLockerClient;
import uk.gov.cslearning.acceptanceTests.data.CourseData;
import uk.gov.cslearning.acceptanceTests.data.ModuleCreationService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class CourseManagementService {

    @Autowired
    ModuleCreationService moduleCreationService;

    final AudienceCreationService audienceCreationService;

    final UserManagementService userManagementService;

    final ElasticsearchClient esClient;

    final LearnerRecordDBClient learnerRecordDBClient;

    final LearningLockerClient learningLockerClient;

    final
    Cache cache;

    public CourseManagementService(AudienceCreationService audienceCreationService, UserManagementService userManagementService, ElasticsearchClient esClient, LearnerRecordDBClient learnerRecordDBClient, LearningLockerClient learningLockerClient, Cache cache) {
        this.audienceCreationService = audienceCreationService;
        this.userManagementService = userManagementService;
        this.esClient = esClient;
        this.learnerRecordDBClient = learnerRecordDBClient;
        this.learningLockerClient = learningLockerClient;
        this.cache = cache;
    }

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
