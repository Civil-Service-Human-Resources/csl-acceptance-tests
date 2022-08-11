package uk.gov.cslearning.acceptanceTests.util;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

@Service
public class LearningRecordUtils {

    public LocalDateTime getToday() {
        return LocalDateTime.now();
    }

    public LocalDateTime getYesterday() {
        return getToday().minus(1, ChronoUnit.DAYS);
    }

    public LocalDateTime getTomorrow() {
        return getToday().plus(1, ChronoUnit.DAYS);
    }
}
