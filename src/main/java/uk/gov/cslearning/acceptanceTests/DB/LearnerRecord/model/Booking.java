package uk.gov.cslearning.acceptanceTests.DB.LearnerRecord.model;

import uk.gov.cslearning.acceptanceTests.Models.builder.BookingBuilder;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name="booking")
public class Booking {

    @Column(name = "learner_id")
    public Long learnerId;

    @Column(name = "event_id")
    public Long eventId;

    @Column(name = "status")
    public String status;

    @Column(name = "booking_time")
    public LocalDateTime bookingTime;

    @Column(name = "accessibility_options")
    public String accessibilityOptions;

    @Column(name = "confirmation_time")
    public LocalDateTime confirmationTime;

    @Column(name = "cancellation_time")
    public LocalDateTime cancellationTime;

    @Column(name = "po_number")
    public String poNumber;

    @Column(name = "cancellation_reason")
    public String cancellationReason;

    @Column(name = "booking_reference")
    public String bookingReference;

    public Booking(Long learnerId, Long eventId, String status, LocalDateTime bookingTime, String accessibilityOptions, LocalDateTime confirmationTime, LocalDateTime cancellationTime, String poNumber, String cancellationReason, String bookingReference) {
        this.learnerId = learnerId;
        this.eventId = eventId;
        this.status = status;
        this.bookingTime = bookingTime;
        this.accessibilityOptions = accessibilityOptions;
        this.confirmationTime = confirmationTime;
        this.cancellationTime = cancellationTime;
        this.poNumber = poNumber;
        this.cancellationReason = cancellationReason;
        this.bookingReference = bookingReference;
    }

    public Booking(BookingBuilder builder) {
        this.learnerId = builder.getLearnerId();
        this.eventId = builder.getEventId();
        this.status = builder.getStatus();
        this.bookingTime = builder.getBookingTime();
        this.accessibilityOptions = builder.getAccessibilityOptions();
        this.confirmationTime = builder.getConfirmationTime();
        this.cancellationTime = builder.getCancellationTime();
        this.poNumber = builder.getPoNumber();
        this.cancellationReason = builder.getCancellationReason();
        this.bookingReference = builder.getBookingReference();
    }
}
