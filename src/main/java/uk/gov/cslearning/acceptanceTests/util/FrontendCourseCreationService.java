package uk.gov.cslearning.acceptanceTests.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.ModuleType;
import uk.gov.cslearning.acceptanceTests.component.Management.ModuleListDetails;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.CourseDetailsPage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.CourseTitlePage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.CourseVisibilityPage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.ModuleCreation.AddModuleBasePage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.ModuleCreation.AddModuleLandingPage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseCreation.ModuleCreation.AddUploadableModulePage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CourseManagementOverviewPage;
import uk.gov.cslearning.acceptanceTests.page.CslManagement.CoursePreviewPage;

import java.util.List;

@Component
public class FrontendCourseCreationService {

    final LoginUtilityService loginUtilityService;

    final CourseVisibilityPage courseVisibilityPage;

    final CourseTitlePage courseTitlePage;

    final CourseDetailsPage courseDetailsPage;

    final CourseManagementOverviewPage courseManagementOverviewPage;

    final CoursePreviewPage coursePreviewPage;

    final AddModuleLandingPage addModuleLandingPage;

    final AddModuleBasePage addModuleBasePage;

    final AddUploadableModulePage addUploadableModulePage;

    public FrontendCourseCreationService(LoginUtilityService loginUtilityService, CourseVisibilityPage courseVisibilityPage, CourseTitlePage courseTitlePage, CourseDetailsPage courseDetailsPage, CourseManagementOverviewPage courseManagementOverviewPage, CoursePreviewPage coursePreviewPage, AddModuleLandingPage addModuleLandingPage, AddModuleBasePage addModuleBasePage, AddUploadableModulePage addUploadableModulePage) {
        this.loginUtilityService = loginUtilityService;
        this.courseVisibilityPage = courseVisibilityPage;
        this.courseTitlePage = courseTitlePage;
        this.courseDetailsPage = courseDetailsPage;
        this.courseManagementOverviewPage = courseManagementOverviewPage;
        this.coursePreviewPage = coursePreviewPage;
        this.addModuleLandingPage = addModuleLandingPage;
        this.addModuleBasePage = addModuleBasePage;
        this.addUploadableModulePage = addUploadableModulePage;
    }

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
