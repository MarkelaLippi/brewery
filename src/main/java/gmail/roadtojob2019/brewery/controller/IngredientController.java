package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.IngredientDto;
import gmail.roadtojob2019.brewery.repository.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/brewery")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("brewer/ingredients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto getIngredient(@PathVariable Long id) {
        return ingredientService.getIngredient(id);
    }

}
