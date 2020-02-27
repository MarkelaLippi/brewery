package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchRecipeNotFoundException;

public interface RecipeService {

    RecipeDto getRecipe(Long id) throws BrewerySuchRecipeNotFoundException;
}
