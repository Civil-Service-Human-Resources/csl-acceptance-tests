package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

import java.util.List;

public class DateRange {
    public List<Integer> date;
    public List<Integer> startTime;
    public List<Integer> endTime;

    public DateRange(List<Integer> date, List<Integer> startTime, List<Integer> endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
