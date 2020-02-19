package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.IngredientDto;
import gmail.roadtojob2019.brewery.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredient ingredientDtoToIngredient(IngredientDto dto);

    IngredientDto ingredientToIngredientDto(Ingredient ingredient);
}
