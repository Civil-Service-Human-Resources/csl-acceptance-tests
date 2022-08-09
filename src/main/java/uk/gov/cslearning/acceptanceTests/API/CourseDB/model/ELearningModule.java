package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

public class ELearningModule extends Module {

    public String startPage;

    public ELearningModule(String url, String id, String title, String description, int duration, int cost, boolean optional, Object status, boolean associatedLearning, String startPage) {
        super("elearning", url, id, title, description, duration, cost, optional, status, associatedLearning, "elearning");
        this.startPage = startPage;
    }
}
