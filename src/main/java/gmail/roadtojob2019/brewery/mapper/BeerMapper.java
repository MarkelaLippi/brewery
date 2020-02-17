package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);

    Recipe recipeDtoToRecipe(RecipeDto recipeDto);

    RecipeDto recipeToRecipeDto(Recipe recipe);
}
