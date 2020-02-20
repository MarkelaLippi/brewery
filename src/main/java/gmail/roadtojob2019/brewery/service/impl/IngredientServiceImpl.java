package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.IngredientDto;
import gmail.roadtojob2019.brewery.entity.Ingredient;
import gmail.roadtojob2019.brewery.mapper.IngredientMapper;
import gmail.roadtojob2019.brewery.repository.IngredientRepository;
import gmail.roadtojob2019.brewery.repository.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    private final IngredientMapper ingredientMapper;

    @PostConstruct
    public void init() {
/*
        ingredientRepository.save(Ingredient.builder()
                .name("Water")
                .amount(100.0)
                .unit("Litre")
                .build());
*/
    }

    @Override
    public IngredientDto getIngredient(Long id) {
        return ingredientMapper.ingredientToIngredientDto(ingredientRepository.getOne(id));
    }
}
