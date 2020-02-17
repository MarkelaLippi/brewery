package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.ComponentDto;
import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Component;
import gmail.roadtojob2019.brewery.entity.Recipe;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe sourceToDestination(RecipeDto source);

    RecipeDto destinationToSource(Recipe destination);

    Set<Component> convertComponentDTOSetToComponentSet(Set<ComponentDto> set);

    Set<ComponentDto> convertComponentSetToComponentDTOSet(Set<Component> set);
}
