package uk.gov.cslearning.acceptanceTests.DB;

import com.mongodb.*;
import org.springframework.stereotype.Service;

@Service
public class LearningLockerClient {

    private final String moduleStatementRegex = "http:\\/\\/cslearning\\.gov\\.uk\\/modules\\/ACCEPTANCE-MODULE";
    private final String eventStatementRegex = "http:\\/\\/cslearning\\.gov\\.uk\\/modules\\/ACCEPTANCE-EVENT";

    private final MongoClient mongoClient;

    public LearningLockerClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void deleteAllAcceptanceTestStatements() {
        DB database = mongoClient.getDB("admin");
        DBCollection statementsColl = database.getCollection("statements");
        DBObject moduleAcceptanceTestStatementsQuery = new BasicDBObject("statement.object.id", new BasicDBObject("$regex", moduleStatementRegex));
        statementsColl.remove(moduleAcceptanceTestStatementsQuery);
        DBObject eventAcceptanceTestStatementsQuery = new BasicDBObject("statement.object.id", new BasicDBObject("$regex", eventStatementRegex));
        statementsColl.remove(eventAcceptanceTestStatementsQuery);
    }
}
