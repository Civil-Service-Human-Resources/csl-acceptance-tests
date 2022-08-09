package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

import java.util.List;

public class EventModule extends Module {

    public List<Event> events;

    public EventModule(String url, String id, String title, String description, int duration, int cost, boolean optional, Object status, boolean associatedLearning, List<Event> events) {
        super("face-to-face", url, id, title, description, duration, cost, optional, status, associatedLearning, "face-to-face");
        this.events = events;
    }
}
