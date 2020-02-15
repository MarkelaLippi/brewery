package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;

import java.util.List;

public interface BrewerService {
    String signIn(BrewerSignInRequestDto request);

    List<ProduceRequestDto> getProduceRequestsByStatus(String status);

    Long changeProduceRequestStatus(Long id, ProduceRequestDto request);

    RecipeDto getRecipe(Long id);

    IngredientDto getIngredient(Long id);

    Long changeBeerAmount(Long id, BeerDto beer);

    ProduceRequestDto getProduceRequest(Long id);
}
