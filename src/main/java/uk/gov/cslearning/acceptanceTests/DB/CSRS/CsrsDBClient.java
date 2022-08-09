package uk.gov.cslearning.acceptanceTests.DB.CSRS;

import com.dieselpoint.norm.Database;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.cslearning.acceptanceTests.DB.DbClient;

import java.sql.Connection;

@Service
public class CsrsDBClient extends DbClient {

    private final Database dbConnection;

    public CsrsDBClient(@Qualifier("CsrsDB") Database dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Database getDb() {
        return dbConnection;
    }

    public String getCivilServantDepCode(String uid) {
        return dbConnection.sql("select ou.code from civil_servant cs join `identity` i on cs.identity_id = i.id join organisational_unit ou on cs.organisational_unit_id = ou.id where i.uid=?", uid).first(String.class);
    }

    public String getCivilServantGradeCode(String uid) {
        return dbConnection.sql("select g.code from civil_servant cs join `identity` i on cs.identity_id = i.id join grade g on cs.grade_id = g.id where i.uid=?", uid).first(String.class);
    }

//    public void deleteCivilServant(String uid) {
//        String statement = String.format("delete from identity where uid = '%s'", uid);
//        execute(statement);
//    }
}
