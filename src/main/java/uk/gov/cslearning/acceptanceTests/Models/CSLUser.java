package uk.gov.cslearning.acceptanceTests.Models;

public class CSLUser {

    public String email;
    public String password;
    public String uid;
    public String departmentCode;
    public String gradeCode;
    public UserType type;

    public CSLUser(String email, String password, String uid, String departmentCode, String gradeCode, UserType type) {
        this.email = email;
        this.password = password;
        this.uid = uid;
        this.departmentCode = departmentCode;
        this.gradeCode = gradeCode;
        this.type = type;
    }
}
