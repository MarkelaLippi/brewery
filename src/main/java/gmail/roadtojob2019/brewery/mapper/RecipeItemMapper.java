package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.RecipeItemDto;
import gmail.roadtojob2019.brewery.entity.RecipeItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RecipeItemMapper {
    @Mappings({
            @Mapping(target = "product.id", source = "productId"),
            @Mapping(target = "recipe.id", source = "recipeId")
    })
    RecipeItem recipeItemDtoToRecipeItem(RecipeItemDto dto);

    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "recipeId", source = "recipe.id")
    })
    RecipeItemDto recipeItemToRecipeItemDto(RecipeItem recipeItem);
}
