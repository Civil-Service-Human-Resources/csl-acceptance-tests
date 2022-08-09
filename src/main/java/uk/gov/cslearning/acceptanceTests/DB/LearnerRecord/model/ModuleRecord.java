package uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model;

import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Module;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name="module_record")
public class ModuleRecord {

    @Column(name="module_id")
    public String moduleId;

    public String state;

    @Column(name="event_id")
    public String eventId;

    @Column(name="completion_date")
    public LocalDateTime completionDate;

    @Column(name="created_at")
    public LocalDateTime createdAt;

    @Column(name="updated_at")
    public LocalDateTime updatedAt;

    @Column(name="module_title")
    public String moduleTitle;

    @Column(name="module_type")
    public String moduleType;

    public int duration;
    public int cost;
    public int optional;

    @Column(name="course_id")
    public String courseId;

    @Column(name="user_id")
    public String userId;

    @Column(name="booking_status")
    public Integer bookingStatus;

    @Column(name="event_date")
    public LocalDateTime eventDate;

    public ModuleRecord(String moduleId, String eventId, String state, LocalDateTime completionDate,
                        LocalDateTime createdAt, LocalDateTime updatedAt, String moduleTitle, String moduleType,
                        int duration, int cost, int optional, String courseId, String userId, Integer bookingStatus,
                        LocalDateTime eventDate) {
        this.moduleId = moduleId;
        this.eventId = eventId;
        this.state = state;
        this.completionDate = completionDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.moduleTitle = moduleTitle;
        this.moduleType = moduleType;
        this.duration = duration;
        this.cost = cost;
        this.optional = optional;
        this.courseId = courseId;
        this.userId = userId;
        this.bookingStatus = bookingStatus;
        this.eventDate = eventDate;
    }

    public static ModuleRecord fromModule(Module module, String eventId, String state, LocalDateTime completionDate,
                                          LocalDateTime createdAt, LocalDateTime updatedAt, String courseId,
                                          String userId, Integer bookingStatus, LocalDateTime eventDate) {
        return new ModuleRecord(
                module.id,
                eventId,
                state,
                completionDate,
                createdAt,
                updatedAt,
                module.title,
                module.type,
                module.duration,
                module.cost,
                module.optional ? 1 : 0,
                courseId,
                userId,
                bookingStatus,
                eventDate
        );
    }

}
