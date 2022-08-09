package uk.gov.cslearning.acceptanceTests.Steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.HomePage;
import uk.gov.cslearning.acceptanceTests.pages.CslUi.SuggestionsForYouPage;

@Component
public class LearningPlanSteps {

    @Autowired
    SuggestionsForYouPage suggestionsForYouPage;

    @Autowired
    HomePage homePage;

    public void addToLearningPlan(String courseTitle) {
        suggestionsForYouPage.navigateTo();
        suggestionsForYouPage.addCourseToLearningPlan(courseTitle);
        homePage.navigateTo();
        homePage.assertCourseJustAdded(courseTitle);
    }

    public void removeFromLearningPlan(String courseTitle) {
        homePage.navigateTo();
        homePage.removeCourseFromLearningPlan(courseTitle);
        homePage.confirmRemoveCourseFromLearningPlan();
    }

    public void removeFromSuggestions(String courseTitle) {
        suggestionsForYouPage.navigateTo();
        suggestionsForYouPage.removeCourseFromSuggestions(courseTitle);
        suggestionsForYouPage.assertCourseRemoved(courseTitle);
    }
}
