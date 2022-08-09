package uk.gov.cslearning.acceptanceTests.Models.builder;

import lombok.Getter;
import uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model.Booking;

import java.time.LocalDateTime;

@Getter
public class BookingBuilder {

    private Long learnerId;
    private Long eventId;
    private String status;
    private String accessibilityOptions;
    private LocalDateTime bookingTime;
    private LocalDateTime confirmationTime;
    private LocalDateTime cancellationTime;
    private String poNumber;
    private String cancellationReason;
    private String bookingReference;

    public BookingBuilder(Long learnerId, Long eventId, LocalDateTime bookingTime, String bookingReference) {
        this.learnerId = learnerId;
        this.eventId = eventId;
        this.bookingTime = bookingTime;
        this.bookingReference = bookingReference;
        this.accessibilityOptions = "";
    }

    public BookingBuilder setCancelled(String cancellationReason, LocalDateTime cancellationTime) {
        this.cancellationReason = cancellationReason;
        this.cancellationTime = cancellationTime;
        this.status = "CANCELLED";
        return this;
    }

    public BookingBuilder setConfirmed(LocalDateTime confirmationTime) {
        this.confirmationTime = confirmationTime;
        this.status = "CONFIRMED";
        return this;
    }

    public BookingBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public BookingBuilder setPoNumber(String poNumber) {
        this.poNumber = poNumber;
        return this;
    }

    public Booking build() {
        return new Booking(this);
    }
}
