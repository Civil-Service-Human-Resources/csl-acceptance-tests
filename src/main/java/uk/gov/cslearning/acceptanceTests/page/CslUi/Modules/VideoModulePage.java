package uk.gov.cslearning.acceptanceTests.page.CslUi.Modules;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslUi.CslUiBasePage;

@Component
public class VideoModulePage extends CslUiBasePage {

    public void completeVideo() {
        boolean success = false;
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        while(!success) {
            try {
                js.executeScript(
                        "document.getElementById('videojs-player_html5_api').currentTime = Math.floor(((document.getElementById('videojs-player_html5_api').duration / 100)*85))", "");
                success = true;
            } catch (JavascriptException jse) {
                wait(1000);
            }
        }
        js.executeScript("document.getElementById('videojs-player_html5_api').play()", "");
        wait(3000);
    }
}
