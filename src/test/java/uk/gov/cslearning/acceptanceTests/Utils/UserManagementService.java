package uk.gov.cslearning.acceptanceTests.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.DB.CSRS.CsrsDBClient;
import uk.gov.cslearning.acceptanceTests.DB.Identity.IdentityDBClient;
import uk.gov.cslearning.acceptanceTests.DB.Identity.model.Reactivation;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;
import uk.gov.cslearning.acceptanceTests.Models.UserType;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserManagementService {

    @Value("${users.learner.email}")
    private String learnerEmail;

    @Value("${users.learner.password}")
    private String learnerPassword;

    @Value("${users.admin.email}")
    private String adminEmail;

    @Value("${users.admin.password}")
    private String adminPassword;

    @Autowired
    IdentityDBClient identityDBClient;

    @Autowired
    CsrsDBClient csrsDBClient;

    private Map<UserType, CSLUser> userMap;

    @PostConstruct
    public void init() {
        userMap = new HashMap<>();
        userMap.put(UserType.LEARNER, createUser(learnerEmail, learnerPassword, UserType.LEARNER));
        userMap.put(UserType.ADMIN, createUser(adminEmail, adminPassword, UserType.ADMIN));
    }

    /*
    * Due to the duplicate token issue, the acceptance tests can sometimes cause problems
    * due to the amount of times they log in. So teardown all tokens related to the tests.
    * */
    public void teardownTokens() {
        Collection<CSLUser> users = userMap.values();
        users.forEach(u -> identityDBClient.deleteTokensForUser(u.uid));
    }

    public void teardownReactivations(String email) {
        identityDBClient.deleteReactivations(email);
    }

    public CSLUser getUser(UserType type) {
        return userMap.get(type);
    }

    public void deleteUser(String email) {
        identityDBClient.fullyDeleteIdentity(email);
    }

    public CSLUser createUser(String email, String password, UserType type) {
        String uid = identityDBClient.getUid(email);
        String departmentCode = csrsDBClient.getCivilServantDepCode(uid);
        String gradeCode = csrsDBClient.getCivilServantGradeCode(uid);
        return new CSLUser(email, password, uid, departmentCode, gradeCode, type);
    }

    public String createReactivationForUser(CSLUser user, LocalDateTime requestedAt) {
        String code = "TEST";
        Reactivation reac = new Reactivation(
                code,
                user.email,
                "PENDING",
                requestedAt,
                null
        );
        identityDBClient.createReactivation(reac);
        return code;
    }

    public String getReactivationCode(String email) {
        return identityDBClient.getReactivationCode(email);
    }

    public void deactivateUser(CSLUser user) {
        identityDBClient.setActivated(user.uid, false);
    }

    public void activateUser(CSLUser user) {
        identityDBClient.setActivated(user.uid, true);
    }

    public String getSignupCodeForUser(String email) {
        return identityDBClient.getSignupCode(email);
    }
}
