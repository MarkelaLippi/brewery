package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe sourceToDestination(RecipeDto source);

    RecipeDto destinationToSource(Recipe destination);
}
