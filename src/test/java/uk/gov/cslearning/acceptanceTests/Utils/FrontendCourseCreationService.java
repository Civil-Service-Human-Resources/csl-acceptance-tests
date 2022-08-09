package uk.gov.cslearning.acceptanceTests.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.ModuleType;
import uk.gov.cslearning.acceptanceTests.Components.Management.ModuleListDetails;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.CourseDetailsPage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.CourseTitlePage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.CourseVisibilityPage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.ModuleCreation.AddModuleBasePage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.ModuleCreation.AddModuleLandingPage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseCreation.ModuleCreation.AddUploadableModulePage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CourseManagementOverviewPage;
import uk.gov.cslearning.acceptanceTests.pages.CslManagement.CoursePreviewPage;

import java.util.List;

@Component
public class FrontendCourseCreationService {

    @Autowired
    LoginUtilityService loginUtilityService;

    @Autowired
    CourseVisibilityPage courseVisibilityPage;

    @Autowired
    CourseTitlePage courseTitlePage;

    @Autowired
    CourseDetailsPage courseDetailsPage;

    @Autowired
    CourseManagementOverviewPage courseManagementOverviewPage;

    @Autowired
    CoursePreviewPage coursePreviewPage;

    @Autowired
    AddModuleLandingPage addModuleLandingPage;

    @Autowired
    AddModuleBasePage addModuleBasePage;

    @Autowired
    AddUploadableModulePage addUploadableModulePage;

    public Module createModule(String courseId, Module module) {
        loginUtilityService.switchToType(UserType.ADMIN);
        return addModule(courseId, module);
    }

    public void createModules(String courseId, List<Module> modules) {
        loginUtilityService.switchToType(UserType.ADMIN);
        for (Module module : modules) {
            addModule(courseId, module);
        }
    }

    private Module addModule(String courseId, Module module) {
        ModuleType type = module.typeAsEnum();
        addModuleLandingPage.navigateTo(courseId);
        addModuleLandingPage.addModule(type);
        addModuleBasePage.setTitleAndDescription(module.title, module.description);
        int[] duration = module.getHoursMinutes();
        addModuleBasePage.setDuration(String.valueOf(duration[0]), String.valueOf(duration[1]));
        if (type == ModuleType.FILE) {
            addUploadableModulePage.uploadPdf();
        }
        addModuleBasePage.addModule();
        ModuleListDetails details = addModuleLandingPage.getModuleDetails(module.title);
        module.id = details.id;
        return module;
    }

    public void createCourse(Course course) {
        loginUtilityService.switchToType(UserType.ADMIN);
        courseVisibilityPage.navigateTo();
        courseVisibilityPage.setCourseVisibility(course.getVisible());
        courseTitlePage.setCourseTitle(course.title, course.topicId);
        courseDetailsPage.setCourseDetails(course.shortDescription, course.description, course.learningOutcomes, course.preparation);
        for (Module module : course.modules) {
            addModule(course.id, module);
        }
    }
}
