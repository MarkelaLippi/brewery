package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.ComponentDto;
import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.Component;
import gmail.roadtojob2019.brewery.entity.Recipe;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe recipeDtoToRecipe(RecipeDto recipeDto);

    RecipeDto recipeToRecipeDto(Recipe recipe);

    Set<Component> convertComponentDTOSetToComponentSet(Set<ComponentDto> set);

    Set<ComponentDto> convertComponentSetToComponentDTOSet(Set<Component> set);

    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);
}
