package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.service.BrewerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrewerServiceImpl implements BrewerService {
    @Override
    public String signIn(BrewerSignInRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    public List<ProduceRequestDto> getAllProduceRequests() {
        return List.of(ProduceRequestDto.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .term(LocalDate.of(2020, 2, 10))
                .status("New")
                .build());
    }

    @Override
    public String changeProduceRequestStatus(Long id, ProduceRequestDto request) {
        ProduceRequestDto requestDto = ProduceRequestDto.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .term(LocalDate.of(2020, 2, 10))
                .status("New")
                .build();
        requestDto.setStatus("In process");
        return "{\"id\":1}";
    }

    @Override
    public RecipeDto getRecipe(Long id) {
        Map<String, Double> components = new HashMap<String, Double>();
        components.put("Water", 2.5);
        components.put("Alcohol", 0.5);
        return new RecipeDto(1L, 1L, components);
    }

    @Override
    public IngredientDto getIngredient(Long id) {
        return IngredientDto.builder()
                .id(1L)
                .name("Malt")
                .amount(20.5)
                .build();
    }

    @Override
    public String changeBeerAmount(Long id, BeerDto beer) {
        Map<String, Double> components = new HashMap<String, Double>();
        components.put("Water", 2.5);
        components.put("Alcohol", 0.5);

        RecipeDto recipe = new RecipeDto(1L, 1L, components);

        BeerDto beerDto = BeerDto.builder()
                .id(1L)
                .name("CoolBeer")
                .type("Светлое")
                .alcohol("4,8%")
                .amount(2540)
                .recipe(recipe)
                .build();
        beerDto.setAmount(2740);
        return "{\"id\":1}";
    }
}
