package uk.gov.cslearning.acceptanceTests.pages.CslUi.CourseOverview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LinkCourseOverviewPage extends CourseOverviewPage {

    public void startModule() {
        this.startLinks.get(0).click();
        closeTab(1);
        refresh();
    }

}
