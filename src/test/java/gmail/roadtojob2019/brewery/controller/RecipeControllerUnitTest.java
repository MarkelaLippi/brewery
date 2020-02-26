package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.entity.Recipe;
import gmail.roadtojob2019.brewery.entity.RecipeItem;
import gmail.roadtojob2019.brewery.repository.ProductRepository;
import gmail.roadtojob2019.brewery.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RecipeControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    void testGetRecipeIsOk() throws Exception {
        // given
        // signInAsCustomer();
        final Optional<Recipe> requiredRecipe = getRecipe();

        willReturn(requiredRecipe).given(recipeRepository)
                .findById(1L);
        // when
        mockMvc.perform(get("/brewery/brewer/recipes/1"))
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
}