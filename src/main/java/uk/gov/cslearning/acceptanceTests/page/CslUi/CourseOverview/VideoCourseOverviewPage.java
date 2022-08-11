package uk.gov.cslearning.acceptanceTests.page.CslUi.CourseOverview;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VideoCourseOverviewPage extends CourseOverviewPage {

    @Value("${pages.csl-ui.modules.video.load-timeout}")
    private int timeoutMs;

    public void startModule() {
        this.startLinks.get(0).click();
//        try {
//            Thread.sleep(timeoutMs);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(String.format("Exception whilst waiting for video to download: %s", e.getMessage()));
//        }
    }
}
