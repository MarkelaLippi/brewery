package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Recipe;
import gmail.roadtojob2019.brewery.mapper.RecipeMapper;
import gmail.roadtojob2019.brewery.repository.RecipeRepository;
import gmail.roadtojob2019.brewery.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    @Override
    @Transactional
    public RecipeDto getRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);
        return recipeDto;
    }
}
