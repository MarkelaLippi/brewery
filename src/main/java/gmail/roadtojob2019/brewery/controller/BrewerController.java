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
    private BrewerService brewerService;

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody BrewerSignInRequestDto request) {
        return brewerService.signIn(request);
    }

/*
    @GetMapping("/requests/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProduceRequestDto> getProduceRequestsByStatus(@PathVariable String status) {
        return brewerService.getProduceRequestsByStatus(status);
    }
*/

/*
    @GetMapping(value = "/requests/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProduceRequestDto getProduceRequest(@PathVariable Long id) {
        return brewerService.getProduceRequest(id);
    }
*/

/*
    @PatchMapping(value = "/requests/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Long changeRequestStatus(@PathVariable Long id, @RequestBody ProduceRequestDto request) {
        return brewerService.changeProduceRequestStatus(id, request);
    }
*/

/*
    @GetMapping("/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipe(@PathVariable Long id) {
        return brewerService.getRecipe(id);
    }
*/

/*
    @GetMapping("/ingredients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto getIngredient(@PathVariable Long id) {
        return brewerService.getIngredient(id);
    }
*/

    @PatchMapping(value = "/beers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Long changeBeerAmount(@PathVariable Long id, @RequestBody BeerDto beer) {
        return brewerService.changeBeerAmount(id, beer);
    }
}
