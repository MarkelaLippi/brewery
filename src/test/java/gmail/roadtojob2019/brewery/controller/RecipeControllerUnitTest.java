package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.entity.Recipe;
import gmail.roadtojob2019.brewery.entity.RecipeItem;
import gmail.roadtojob2019.brewery.repository.RecipeRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RecipeControllerUnitTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    void testGetRecipeIsOk() throws Exception {
        // given
        final Optional<Recipe> requiredRecipe = getRecipe();
        willReturn(requiredRecipe).given(recipeRepository).findById(1L);
        String token = signInAsUser(UserRole.BREWER);
        // when
        mockMvc.perform(get("/brewery/brewer/recipes/1").header("Authorization", token))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(" {\n" +
                        "\"id\" : 1," +
                        "\"productId\" : 1," +
                        "\"recipeItemDtos\" : [\n" +
                        "                       {\"productId\" : 2,\n" +
                        "                        \"amount\" : 3.0 }\n" +
                        "                               ]\n" +
                        "  }\n"));
        verify(recipeRepository, times(1)).findById(any(Long.class));
    }

    private Optional<Recipe> getRecipe() {
        final RecipeItem recipeItem = RecipeItem.builder()
                .id(1L)
                .amount(3.0)
                .product(Product.builder().id(2L).build())
                .build();

        return Optional.of(Recipe.builder()
                .id(1L)
                .productId(1L)
                .recipeItems(List.of(recipeItem))
                .build());
    }

    @Test
    void testGetRecipeThrowsBrewerySuchRecipeNotFoundException() throws Exception {
        // given
        willReturn(Optional.empty()).given(recipeRepository).findById(1L);
        String token = signInAsUser(UserRole.BREWER);
        // when
        mockMvc.perform(get("/brewery/brewer/recipes/1").header("Authorization", token))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Recipe with id = 1 was not found"));
        verify(recipeRepository, times(1)).findById(any(Long.class));
        verify(recipeRepository, times(0)).save(any(Recipe.class));
    }
}