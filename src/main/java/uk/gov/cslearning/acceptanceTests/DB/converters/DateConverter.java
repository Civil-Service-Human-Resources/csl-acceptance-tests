package uk.gov.cslearning.acceptanceTests.DB.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<Timestamp, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    @Override
    public Timestamp convertToEntityAttribute(LocalDateTime localDateTime) {
        return new Timestamp(localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
    }
}
