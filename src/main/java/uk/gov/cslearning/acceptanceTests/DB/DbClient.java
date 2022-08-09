package uk.gov.cslearning.acceptanceTests.DB;

import com.dieselpoint.norm.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DbClient {

    public abstract Database getDb();

//    protected void execute(String sql) {
//        try {
//            PreparedStatement ps = getDbConnection().prepareStatement(sql);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(String.format("Error when executing SQL statement: %s", e.getMessage()));
//        }
//
//    }
//
//    protected Object executeAndFetchOne(String sql, String col) {
//        try {
//            PreparedStatement ps = getDbConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            boolean res = rs.next();
//            if (!res) {
//                return null;
//            } else {
//                return rs.getObject(col);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(String.format("Error when executing SQL query: %s", e.getMessage()));
//        }
//    }

}
