package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/brewery")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("brewer/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }
}

