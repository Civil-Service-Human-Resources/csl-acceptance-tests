package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public String id;
    public String title;
    public String shortDescription;
    public String description;
    public String learningOutcomes;
    public List<Module> modules;
    public LearningProvider learningProvider;
    public ArrayList<Audience> audiences;
    public String preparation;
    public Owner owner;
    public String visibility;
    public String status;
    public String topicId;

    public Course(String id, String title, String shortDescription, String description, String learningOutcomes, List<Module> modules, LearningProvider learningProvider, ArrayList<Audience> audiences, String preparation, Owner owner, String visibility, String status, String topicId) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.learningOutcomes = learningOutcomes;
        this.modules = modules;
        this.learningProvider = learningProvider;
        this.audiences = audiences;
        this.preparation = preparation;
        this.owner = owner;
        this.visibility = visibility;
        this.status = status;
        this.topicId = topicId;
    }

    public boolean getVisible() {
        return visibility.equals("PUBLIC");
    }

}
