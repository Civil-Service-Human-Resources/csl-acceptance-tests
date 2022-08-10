package uk.gov.cslearning.acceptanceTests.DB.Identity.model;

import uk.gov.cslearning.acceptanceTests.DB.converters.DateConverter;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "reactivation")
public class Reactivation {

    @Id
    public long id;
    public String code;
    public String email;
    public String reactivation_status;
    public LocalDateTime requested_at;
    public LocalDateTime reactivated_at;

    public Reactivation() {}

    public Reactivation(String code, String email, String reactivation_status, LocalDateTime requested_at, LocalDateTime reactivated_at) {
        this.code = code;
        this.email = email;
        this.reactivation_status = reactivation_status;
        this.requested_at = requested_at;
        this.reactivated_at = reactivated_at;
    }
}
