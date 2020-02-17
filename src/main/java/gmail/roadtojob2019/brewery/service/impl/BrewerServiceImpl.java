package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import gmail.roadtojob2019.brewery.mapper.IngredientMapper;
import gmail.roadtojob2019.brewery.mapper.ProduceRequestMapper;
import gmail.roadtojob2019.brewery.mapper.RecipeMapper;
import gmail.roadtojob2019.brewery.repository.*;
import gmail.roadtojob2019.brewery.service.BrewerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrewerServiceImpl implements BrewerService {

    private final ProduceRequestRepository produceRequestRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final BeerRepository beerRepository;

    private final ProduceRequestMapper produceRequestMapper;
    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;

    @Override
    public String signIn(BrewerSignInRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    public List<ProduceRequestDto> getProduceRequestsByStatus(String status) {
        return produceRequestRepository
                .findByStatusIgnoreCase(status)
                .stream()
                .map(produceRequestMapper::produceRequestToProduceRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProduceRequestDto getProduceRequest(Long id) {
        return produceRequestMapper.produceRequestToProduceRequestDto(produceRequestRepository.findById(id).get());
    }

    @Override
    public Long changeProduceRequestStatus(Long id, ProduceRequestDto produceRequestDto) {
        ProduceRequest produceRequest = produceRequestRepository.findById(id).get();
        produceRequest.setStatus(produceRequestDto.getStatus());
        return produceRequest.getId();
    }

    @Override
    public RecipeDto getRecipe(Long id) {
        return recipeMapper.recipeToRecipeDto(recipeRepository.getOne(id));

    }

    @Override
    public IngredientDto getIngredient(Long id) {
        return ingredientMapper.destinationToSource(ingredientRepository.getOne(id));
    }

    @Override
    public Long changeBeerAmount(Long id, BeerDto beer) {
        Beer modifiedBeer = beerRepository.getOne(id);
        modifiedBeer.setAmount(beer.getAmount());
        Beer savedBeer = beerRepository.save(modifiedBeer);
        return savedBeer.getId();
    }
}
