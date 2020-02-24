package gmail.roadtojob2019.brewery.converters;

import gmail.roadtojob2019.brewery.entity.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status priority) {
        if (priority == null) {
            return null;
        }
        return priority.toString();
    }

    @Override
    public Status convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        try {
            return Status.valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}