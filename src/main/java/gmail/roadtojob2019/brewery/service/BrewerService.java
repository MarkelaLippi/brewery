package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.*;

import java.util.List;

public interface BrewerService {
    String signIn(BrewerSignInRequestDto request);

    List<ProduceRequestDto> getAllProduceRequests();

    String changeProduceRequestStatus(Long id, ProduceRequestDto request);

    RecipeDto getRecipe(Long id);

    IngredientDto getIngredient(Long id);

    String changeBeerAmount(Long id, BeerDto beer);
}
