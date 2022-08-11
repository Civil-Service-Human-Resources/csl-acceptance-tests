package uk.gov.cslearning.acceptanceTests.page.CslUi.CourseOverview;

import org.springframework.stereotype.Component;

@Component
public class LinkCourseOverviewPage extends CourseOverviewPage {

    public void startModule() {
        this.startLinks.get(0).click();
        closeTab(1);
        refresh();
    }

}
