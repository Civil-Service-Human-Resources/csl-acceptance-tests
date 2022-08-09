package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

public class Module{
    public String type;
    public String url;
    public String id;
    public String title;
    public String description;
    public int duration;
    public int cost;
    public boolean optional;
    public Object status;
    public boolean associatedLearning;
    public String moduleType;

    public Module(String type, String url, String id, String title, String description, int duration, int cost, boolean optional, Object status, boolean associatedLearning, String moduleType) {
        this.type = type;
        this.url = url;
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.cost = cost;
        this.optional = optional;
        this.status = status;
        this.associatedLearning = associatedLearning;
        this.moduleType = moduleType;
    }

    public ModuleType typeAsEnum() {
        return switch (type) {
            case "link" -> ModuleType.LINK;
            case "mp4" -> ModuleType.MP4;
            case "youtube" -> ModuleType.YOUTUBE;
            case "file" -> ModuleType.FILE;
            case "face-to-face" -> ModuleType.FACETOFACE;
            case "e-learning" -> ModuleType.ELEARNING;
            default -> throw new RuntimeException(String.format("%s is not a valid module type", type));
        };
    }

    public int[] getHoursMinutes() {
        int hours = (int) Math.floor(duration / 3600);
        int minutes = (int) Math.floor((duration % 36000) / 60);
        return new int[]{hours, minutes};
    }
}
