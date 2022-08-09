package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

public class Venue {
    public String location;
    public String address;
    public int capacity;
    public int minCapacity;
    public int availability;

    public Venue(String location, String address, int capacity, int minCapacity, int availability) {
        this.location = location;
        this.address = address;
        this.capacity = capacity;
        this.minCapacity = minCapacity;
        this.availability = availability;
    }
}
