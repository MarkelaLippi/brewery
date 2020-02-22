package gmail.roadtojob2019.brewery.converters;

import gmail.roadtojob2019.brewery.entity.Type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type priority) {
        if (priority == null) {
            return null;
        }
        return priority.toString();
    }

    @Override
    public Type convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        try {
            return Type.valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}