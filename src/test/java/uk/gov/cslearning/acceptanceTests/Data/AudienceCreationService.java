package uk.gov.cslearning.acceptanceTests.Data;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Audience;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
@Service
public class AudienceCreationService {

    private String generateAudienceId() {
        String randomID = RandomStringUtils.randomAlphabetic(2);
        return String.format("ACCEPTANCE-AUDIENCE-%s", randomID);
    }

    public Audience generateGenericAudience() {
        String id = generateAudienceId();
        String name = String.format("ACC: %s", id);
        return new Audience(
                id,
                name,
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                null,
                null,
                "OPEN",
                null);
    }

    public Audience generateAudienceForUser(CSLUser user) {
        Audience aud = generateGenericAudience();
        aud.departments.add(user.departmentCode);
        aud.grades.add(user.gradeCode);
        return aud;
    }

    public Audience generateRequiredAudienceForUser(CSLUser user, LocalDateTime requiredBy) {
        Audience aud = generateGenericAudience();
        aud.departments.add(user.departmentCode);
        aud.requiredBy = requiredBy.atZone(ZoneId.systemDefault()).toInstant();
        aud.type = "REQUIRED_LEARNING";
        aud.frequency = "P1Y";
        return aud;
    }
}
