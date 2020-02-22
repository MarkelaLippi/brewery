package gmail.roadtojob2019.brewery.converters;

import gmail.roadtojob2019.brewery.entity.Unit;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UnitConverter implements AttributeConverter<Unit, String> {

    @Override
    public String convertToDatabaseColumn(Unit priority) {
        if (priority == null) {
            return null;
        }
        return priority.toString();
    }

    @Override
    public Unit convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        try {
            return Unit.valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}