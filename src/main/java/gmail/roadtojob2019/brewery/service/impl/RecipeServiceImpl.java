package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.Component;
import gmail.roadtojob2019.brewery.entity.Recipe;
import gmail.roadtojob2019.brewery.mapper.RecipeMapper;
import gmail.roadtojob2019.brewery.repository.RecipeRepository;
import gmail.roadtojob2019.brewery.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    @PostConstruct
    public void init() {
/*
        Set<Component> components = new HashSet<>();
        recipeRepository.save(Recipe.builder()
                .components(components)
                .build());
*/
    }

    @Override
    public RecipeDto getRecipe(Long id) {
        return recipeMapper
                .recipeToRecipeDto(recipeRepository.getOne(id));

    }
}
