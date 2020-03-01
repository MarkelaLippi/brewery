package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchRecipeNotFoundException;
import gmail.roadtojob2019.brewery.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "Access to recipes")
@RequestMapping("/brewery")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("brewer/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get recipe")
    public RecipeDto getRecipe(@PathVariable final Long id) throws BrewerySuchRecipeNotFoundException {
        return recipeService.getRecipe(id);
    }
}

