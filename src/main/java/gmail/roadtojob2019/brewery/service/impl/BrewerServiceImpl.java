package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.service.BrewerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public void getRecipe(Long id) {

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

        return "{\"id\":1}";
    }
}
