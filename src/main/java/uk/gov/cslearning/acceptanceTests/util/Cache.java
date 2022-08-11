package uk.gov.cslearning.acceptanceTests.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.Models.UserType;
import uk.gov.cslearning.acceptanceTests.Models.CSLUser;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope("singleton")
public class Cache {

    public Map<UserType, CSLUser> users = new HashMap<>();

    private CSLUser currentUser;

    public CSLUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CSLUser user) {
        currentUser = user;
    }

    public void clearCurrentUser() {
        currentUser = null;
    }

    public CSLUser getUser(UserType type) {
        return users.get(type);
    }

    public void setUser(UserType type, CSLUser user) {
        users.put(type, user);
    }
}
