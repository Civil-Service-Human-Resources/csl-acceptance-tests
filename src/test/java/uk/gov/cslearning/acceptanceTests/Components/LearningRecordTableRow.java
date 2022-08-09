package uk.gov.cslearning.acceptanceTests.Components;

import org.springframework.util.Assert;

public class LearningRecordTableRow {

    public String title;
    public String type;
    public String duration;
    public String dateCompleted;

    public LearningRecordTableRow(String title, String type, String duration, String dateCompleted) {
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.dateCompleted = dateCompleted;
    }

    public void assertType(String expectedType) {
        Assert.isTrue((type.equals(expectedType)), String.format("Course type was not %s, was %s", expectedType, type));
    }
}
