package gmail.roadtojob2019.brewery.converters;

import gmail.roadtojob2019.brewery.security.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole priority) {
        if (priority == null) {
            return null;
        }
        return priority.toString();
    }

    @Override
    public UserRole convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        try {
            return UserRole.valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}