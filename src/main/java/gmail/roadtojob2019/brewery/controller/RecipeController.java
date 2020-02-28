package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchRecipeNotFoundException;
import gmail.roadtojob2019.brewery.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brewery")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("brewer/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipe(@PathVariable final Long id) throws BrewerySuchRecipeNotFoundException {
        return recipeService.getRecipe(id);
    }
}

