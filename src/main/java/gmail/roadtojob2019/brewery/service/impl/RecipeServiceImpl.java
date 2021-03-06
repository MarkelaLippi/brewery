package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Recipe;
import gmail.roadtojob2019.brewery.exception.BrewerySuchRecipeNotFoundException;
import gmail.roadtojob2019.brewery.mapper.RecipeMapper;
import gmail.roadtojob2019.brewery.repository.RecipeRepository;
import gmail.roadtojob2019.brewery.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    @Override
    @Transactional
    public RecipeDto getRecipe(final Long id) throws BrewerySuchRecipeNotFoundException {
        final Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new BrewerySuchRecipeNotFoundException("Recipe with id = " + id + " was not found"));
        final RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);
        return recipeDto;
    }
}
