package uk.gov.cslearning.acceptanceTests.DB;

import com.dieselpoint.norm.Database;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConfig {

    @Value("${db.identity.connection-string}")
    private String identityConnectionString;

    @Value("${db.learner-record.connection-string}")
    private String learnerRecordConnectionString;

    @Value("${db.csrs.connection-string}")
    private String csrsConnectionString;

    @Value("${db.mongo.connection-string}")
    private String mongoConnectionString;

    @Bean
    public MongoClient mongoClient() {
        try {
            return new MongoClient(new MongoClientURI(mongoConnectionString));
        } catch (UnknownHostException e) {
            throw new RuntimeException(String.format("Failed to create MongoDB connection: %s", e.getMessage()));
        }
    }

    @Bean("IdentityDB")
    public Database identityConnection() {
        Database db = new Database();
        db.setJdbcUrl(identityConnectionString);
        return db;
    }

    @Bean("LearnerRecordDB")
    public Database learnerRecordConnection() {
        Database db = new Database();
        db.setJdbcUrl(learnerRecordConnectionString);
        return db;
    }

    @Bean("CsrsDB")
    public Database csrsConnection() {
        Database db = new Database();
        db.setJdbcUrl(csrsConnectionString);
        return db;
    }
}
