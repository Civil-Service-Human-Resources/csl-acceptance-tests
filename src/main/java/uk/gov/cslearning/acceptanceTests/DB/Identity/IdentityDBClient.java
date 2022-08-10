package uk.gov.cslearning.acceptanceTests.DB.Identity;

import com.dieselpoint.norm.Database;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.DB.DbClient;
import uk.gov.cslearning.acceptanceTests.DB.Identity.model.Reactivation;

import java.sql.Connection;

@Service
public class IdentityDBClient extends DbClient {

    private final Database dbConnection;

    public IdentityDBClient(@Qualifier("IdentityDB") Database dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Database getDb() {
        return dbConnection;
    }

    public void createReactivation(Reactivation reactivation) {
        dbConnection.insert(reactivation);
    }

    public String getReactivationCode(String email) {
        return dbConnection.sql("select code from reactivation where email=?", email).first(String.class);
    }

    /*
    * Delete any invites for the user as well as their identity
    * */
    public void fullyDeleteIdentity(String email) {
        deleteIdentity(email);
        deleteInvite(email);
    }

    public void setActivated(String uid, boolean active) {
        dbConnection.table("identity").sql("update identity set active = ? where uid = ?", active ? 1 : 0, uid).execute();
    }

    public void deleteReactivations(String email)  {
        dbConnection.table("reactivation").where("email=?", email).delete();
    }

    public void deleteIdentity(String email) {
        dbConnection.table("identity").where("email=?", email).delete();
    }

    public void deleteInvite(String email) {
        dbConnection.table("invite").where("for_email=?", email).delete();
    }

    public void deleteTokensForUser(String uid) {
        dbConnection.table("token").where("user_name=?", uid).delete();
    }

    public String getSignupCode(String email) {
        return dbConnection.sql("select code from invite where for_email = ?", email).first(String.class);
    }

    public String getUid(String email) {
        return dbConnection.sql("select uid from identity where email = ?", email).first(String.class);
    }

}
