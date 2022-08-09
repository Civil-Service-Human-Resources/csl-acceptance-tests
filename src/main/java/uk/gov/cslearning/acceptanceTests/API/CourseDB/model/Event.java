package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    public String id;
    public String joiningInstructions;
    public Venue venue;
    public List<DateRange> dateRanges;
    public String status;
    public String cancellationReason;

    public Event(String id, String joiningInstructions, Venue venue, List<DateRange> dateRanges, String status, String cancellationReason) {
        this.id = id;
        this.joiningInstructions = joiningInstructions;
        this.venue = venue;
        this.dateRanges = dateRanges;
        this.status = status;
        this.cancellationReason = cancellationReason;
    }

    @JsonIgnore
    public LocalDateTime getEventDate() {
        DateRange dr = dateRanges.get(0);
        int year = dr.date.get(0);
        int month = dr.date.get(1);
        int day = dr.date.get(2);
        int hour = dr.startTime.get(0);
        int minute = dr.startTime.get(1);
        return LocalDateTime.of(year, month, day, hour, minute);
    }

}
