package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.service.BrewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brewery/brewer")
public class BrewerController {

    @Autowired
    private BrewerService service;

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody BrewerSignInRequestDto request) {
        return service.signIn(request);
    }

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<ProduceRequestDto> getAllRequests() {
        return service.getAllProduceRequests();
    }

    @PutMapping(value = "/requests/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String changeRequestStatus(@PathVariable Long id, @RequestBody ProduceRequestDto request) {
        return service.changeProduceRequestStatus(id, request);
    }

    @GetMapping("/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipe(@PathVariable Long id) {
        return service.getRecipe(id);
    }

    @GetMapping("/ingredients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto getIngredient(@PathVariable Long id) {
        return service.getIngredient(id);
    }

    @PutMapping(value = "/beers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String changeBeerAmount(@PathVariable Long id, @RequestBody BeerDto beer) {
        return service.changeBeerAmount(id, beer);
    }
}
