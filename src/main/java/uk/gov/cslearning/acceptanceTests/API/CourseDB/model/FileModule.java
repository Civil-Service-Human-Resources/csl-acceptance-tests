package uk.gov.cslearning.acceptanceTests.API.CourseDB.model;

public class FileModule extends Module {

    public File file;

    public FileModule(String url, String id, String title, String description, int duration, int cost, boolean optional, Object status, boolean associatedLearning, File file) {
        super("file", url, id, title, description, duration, cost, optional, status, associatedLearning, "file");
        this.file = file;
    }

    public FileModule(Module module, File file) {
        super("file", module.url, module.id, module.title, module.description, module.duration, module.cost, module.optional, module.status, module.associatedLearning, "file");
        this.file = file;
    }
}
