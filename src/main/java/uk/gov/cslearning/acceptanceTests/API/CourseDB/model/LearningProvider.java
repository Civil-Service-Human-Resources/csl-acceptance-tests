package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

import java.util.ArrayList;

public class LearningProvider{
    public String id;
    public String name;
    public ArrayList<String> termsAndConditions;
    public ArrayList<String> cancellationPolicies;

    public LearningProvider(String id, String name, ArrayList<String> termsAndConditions, ArrayList<String> cancellationPolicies) {
        this.id = id;
        this.name = name;
        this.termsAndConditions = termsAndConditions;
        this.cancellationPolicies = cancellationPolicies;
    }
}
