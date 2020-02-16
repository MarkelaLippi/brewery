package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.IngredientDto;
import gmail.roadtojob2019.brewery.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredient sourceToDestination(IngredientDto source);

    IngredientDto destinationToSource(Ingredient destination);
}
