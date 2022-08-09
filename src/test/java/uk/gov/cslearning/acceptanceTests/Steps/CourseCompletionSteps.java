package uk.gov.cslearning.acceptanceTests.Steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import uk.gov.cslearning.acceptanceTests.Components.DisciteStatus;
import uk.gov.cslearning.acceptanceTests.Utils.SeleniumUtils;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseOverview.*;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.LearningRecordPage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.ELearning.BasicGomoPage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.Modules.VideoModulePage;

import java.time.LocalDateTime;

@Component
public class CourseCompletionSteps {

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
    BasicGomoPage basicGomoPage;

    @Autowired
    VideoModulePage videoModulePage;

    @Autowired
    SeleniumUtils seleniumUtils;

    public void assertCourseInProgressOnHomepage(String courseTitle) {
        homePage.navigateTo();
        homePage.assertCourseInProgress(courseTitle);
    }

    public void assertEventIsRequestedOnHomepage(String moduleTitle) {
        homePage.navigateTo();
        homePage.assertEventRequested(moduleTitle);
    }

    public void assertEventIsConfirmedOnHomepage(String moduleTitle) {
        homePage.navigateTo();
        homePage.assertEventConfirmed(moduleTitle);
    }

    public void assertEventIsCancelledOnHomepage(String moduleTitle) {
        homePage.navigateTo();
        homePage.assertEventCancelled(moduleTitle);
    }

    public void assertCourseCompletedOnLearningRecord(String courseTitle, LocalDateTime date) {
        learningRecordPage.navigateTo();
        learningRecordPage.assertCourseCompleteWithDate(courseTitle, date);
    }

    public void assertModuleInProgressOnCourseOverview(String courseId, String moduleTitle) {
        courseOverviewPage.navigateTo(courseId);
        courseOverviewPage.assertModuleStatus(moduleTitle, DisciteStatus.IN_PROGRESS, 5);
    }

    public void assertModuleCompletedOnCourseOverview(String courseId, String moduleTitle) {
        courseOverviewPage.navigateTo(courseId);
        courseOverviewPage.assertModuleStatus(moduleTitle, DisciteStatus.COMPLETED, 5);
    }

    public void completeLinkModule(String courseId) {
        if (!seleniumUtils.getCurrentUrl().equals(linkCourseOverviewPage.getUrl(courseId))) {
            linkCourseOverviewPage.navigateTo(courseId);
        }
        linkCourseOverviewPage.startModule();
    }

    public void completeFileModule(String courseId, String documentName) {
        if (!seleniumUtils.getCurrentUrl().equals(fileCourseOverviewPage.getUrl(courseId))) {
            fileCourseOverviewPage.navigateTo(courseId);
        }
        fileCourseOverviewPage.start(documentName);
    }

    public void startVideoModule(String courseId) {
        if (!seleniumUtils.getCurrentUrl().equals(videoCourseOverviewPage.getUrl(courseId))) {
            videoCourseOverviewPage.navigateTo(courseId);
        }
        videoCourseOverviewPage.startModule();
    }

    public void completeVideoModule(String courseId) {
        startVideoModule(courseId);
        videoModulePage.completeVideo();
    }

    public void startELearningModule(String courseId) {
        if (!seleniumUtils.getCurrentUrl().equals(eLearningCourseOverviewPage.getUrl(courseId))) {
            eLearningCourseOverviewPage.navigateTo(courseId);
        }
        eLearningCourseOverviewPage.startModule();
    }

    public void completeELearningModule(String courseId) {
        startELearningModule(courseId);
        seleniumUtils.wait(2000);
        basicGomoPage.scrollToBottom();
        seleniumUtils.wait(4000);
    }

    public void assertCourseNotOnHomepage(String courseTitle) {
        homePage.navigateTo();
        Assert.isTrue(!homePage.isCourseOnHomepage(courseTitle), String.format("Expected course '%s' to not appear on homepage but it was.", courseTitle));
    }

    public void assertCourseOnHomepage(String courseTitle) {
        homePage.navigateTo();
        Assert.isTrue(homePage.isCourseOnHomepage(courseTitle), String.format("Expected course '%s' to appear on homepage but it wasn't.", courseTitle));
    }

    public void doModuleOnBlendedCourse(String courseId, String moduleTitle) {
        if (!seleniumUtils.getCurrentUrl().equals(courseOverviewPage.getUrl(courseId))) {
            courseOverviewPage.navigateTo(courseId);
        }
        courseOverviewPage.startModule(moduleTitle);
    }

    public void completeVideoOnBlendedCourse(String courseId, String moduleTitle) {
        doModuleOnBlendedCourse(courseId, moduleTitle);
        videoModulePage.completeVideo();
    }

    public void completeELearningOnBlendedCourse(String courseId, String moduleTitle) {
        doModuleOnBlendedCourse(courseId, moduleTitle);
        basicGomoPage.scrollToBottom();
    }

    public void completeCourseEventOnHomepage(String courseTitle) {
        homePage.navigateTo();
        homePage.completeEventCourse(courseTitle);
    }

    public void didNotAttendCourseEventOnHomepage(String courseTitle) {
        homePage.navigateTo();
        homePage.didNotAttendEventCourse(courseTitle);
    }

}
