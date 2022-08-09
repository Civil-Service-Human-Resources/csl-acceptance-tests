package uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model;

import javax.persistence.Table;

@Table(name="event")
public class Event {

    public String uid;
    public String path;
    public String status;
    public String cancellation_reason;

    public Event(String uid, String path, String status, String cancellation_reason) {
        this.uid = uid;
        this.path = path;
        this.status = status;
        this.cancellation_reason = cancellation_reason;
    }
}
