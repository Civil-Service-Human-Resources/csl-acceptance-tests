package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Audience{
    public String id;
    public String name;
    public List<String> areasOfWork;
    public List<String> departments;
    public List<String> grades;
    public List<String> interests;
    public Instant requiredBy;
    public String frequency;
    public String type;
    public String eventId;

    public Audience(String id, String name, List<String> areasOfWork, List<String> departments, List<String> grades, List<String> interests, Instant requiredBy, String frequency, String type, String eventId) {
        this.id = id;
        this.name = name;
        this.areasOfWork = areasOfWork;
        this.departments = departments;
        this.grades = grades;
        this.interests = interests;
        this.requiredBy = requiredBy;
        this.frequency = frequency;
        this.type = type;
        this.eventId = eventId;
    }
}
