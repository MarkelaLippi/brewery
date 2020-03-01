package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.dto.RecipeItemDto;
import gmail.roadtojob2019.brewery.entity.Recipe;
import gmail.roadtojob2019.brewery.entity.RecipeItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mappings({
            @Mapping(target = "recipeItemDtos", source = "recipeItems")
    })
    RecipeDto recipeToRecipeDto(Recipe recipe);

    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "recipeId", source = "recipe.id")
    })
    RecipeItemDto recipeItemToRecipeItemDto(RecipeItem recipeItem);

    List<RecipeItemDto> convertRecipeItemListToRecipeItemDtoList(List<RecipeItem> recipeItems);

}
