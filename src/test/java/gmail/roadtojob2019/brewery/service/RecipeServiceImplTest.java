package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.RecipeDto;
import gmail.roadtojob2019.brewery.entity.Recipe;
import gmail.roadtojob2019.brewery.mapper.RecipeMapper;
import gmail.roadtojob2019.brewery.repository.RecipeRepository;
import gmail.roadtojob2019.brewery.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class RecipeServiceImplTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;

    @Test
    void testGetRecipe() {
        final Long recipeId=1L;
        final Long productId=1L;
        final Optional<Recipe> recipe = Optional.of(Recipe.builder()
                .id(recipeId)
                .productId(productId)
                .build());
        final RecipeDto recipeDto = RecipeDto.builder()
                .id(recipeId)
                .productId(productId)
                .build();

        willReturn(recipe).given(recipeRepository)
                .findById(recipeId);

        willReturn(recipeDto).given(recipeMapper)
                .recipeToRecipeDto(recipe.get());

        final RecipeDto receivedRecipeDto = recipeService.getRecipe(recipeId);

        assertEquals(receivedRecipeDto.getId(), recipe.get().getId());
        assertEquals(receivedRecipeDto.getProductId(), recipe.get().getProductId());
    }
}