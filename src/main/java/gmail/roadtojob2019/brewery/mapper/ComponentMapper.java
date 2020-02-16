package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.ComponentDto;
import gmail.roadtojob2019.brewery.entity.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ComponentMapper {

    Component componentDtoToComponent(ComponentDto dto);

    @Mappings({
            @Mapping(target = "ingredientId", source = "component.ingredient.id"),
            @Mapping(target = "ingredientName", source = "component.ingredient.name"),
            @Mapping(target = "unit", source = "component.ingredient.unit")
    })
    ComponentDto componentToComponentDto(Component component);
}
