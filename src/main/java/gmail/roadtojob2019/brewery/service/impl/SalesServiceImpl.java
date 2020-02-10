package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.service.SalesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesServiceImpl implements SalesService {
    @Override
    public String signIn(SalesSignInRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return List.of(OrderDto.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .customer_id(1L)
                .build());
    }

    @Override
    public List<BeerDto> getAllBeers() {
        Map<String, Double> components = new HashMap<String, Double>();
        components.put("Water", 2.5);
        components.put("Alcohol", 0.5);

        RecipeDto recipe = new RecipeDto(1L, 1L, components);

        return List.of(BeerDto.builder()
                .id(1L)
                .name("CoolBeer")
                .type("Светлое")
                .alcohol("4,8%")
                .amount(2540)
                .recipe(recipe)
                .build());
    }

    @Override
    public String makeRequest(ProduceRequestDto request) {
        ProduceRequestDto requestDto = ProduceRequestDto.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .term(LocalDate.of(2020, 2, 10))
                .status("New")
                .build();
        return "{\"id\":1}";
    }
}

