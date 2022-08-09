package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

public class Owner{
    public String scope;
    public String organisationalUnit;
    public int profession;
    public String supplier;

    public Owner(String scope, String organisationalUnit, int profession, String supplier) {
        this.scope = scope;
        this.organisationalUnit = organisationalUnit;
        this.profession = profession;
        this.supplier = supplier;
    }
}
