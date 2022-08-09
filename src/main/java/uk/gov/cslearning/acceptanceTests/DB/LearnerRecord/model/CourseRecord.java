package uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model;

import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name="course_record")
public class CourseRecord {

    @Column(name="course_id")
    public String courseId;

    @Column(name="user_id")
    public String userId;

    @Column(name="course_title")
    public String courseTitle;
    public String state;

    @Column(name="is_required")
    public int isRequired;

    @Column(name="last_updated")
    public LocalDateTime lastUpdated;

    public CourseRecord(String courseId, String userId, String courseTitle, String state, int isRequired, LocalDateTime lastUpdated) {
        this.courseId = courseId;
        this.userId = userId;
        this.courseTitle = courseTitle;
        this.state = state;
        this.isRequired = isRequired;
        this.lastUpdated = lastUpdated;
    }

    public static CourseRecord fromCourse(Course course, String userId, String state, int required, LocalDateTime lastUpdated) {
        return new CourseRecord(
                course.id,
                userId,
                course.title,
                state,
                required,
                lastUpdated
        );
    }
}
