package uk.gov.cslearning.acceptanceTests.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslUi.HomePage;

@Component
public class SearchSteps {

    @Autowired
    HomePage homePage;

    public void searchFromHomepage(String query) {
        homePage.navigateTo();
        homePage.search(query);
    }
}
