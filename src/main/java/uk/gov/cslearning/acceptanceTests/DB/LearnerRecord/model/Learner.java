package uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model;

import javax.persistence.Table;

@Table(name="learner")
public class Learner {
    public String uid;
    public String learner_email;

    public Learner(String uid, String learner_email) {
        this.uid = uid;
        this.learner_email = learner_email;
    }
}
