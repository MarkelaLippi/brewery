package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.*;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.repository.IngredientRepository;
import gmail.roadtojob2019.brewery.repository.ProduceRequestRepository;
import gmail.roadtojob2019.brewery.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BrewerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProduceRequestRepository produceRequestRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private BeerRepository beerRepository;

    @Test
    public void testBrewerSignInIsOk() throws Exception {
        mockMvc.perform(post("/brewery/brewer/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Sidorov@gmail.com\",\n" +
                        "  \"password\" : \"11223344\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    void testGetProduceRequestsByStatusIsOk() throws Exception {
        produceRequestRepository.save(ProduceRequest.builder()
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .term(LocalDate.of(2020, 2, 10))
                .status("New")
                .build());
        mockMvc.perform(get("/brewery/brewer/requests/new"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : 200,\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"status\" : \"New\"\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    void testGetProduceRequestIsOk() throws Exception {
        produceRequestRepository.save(ProduceRequest.builder()
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .term(LocalDate.of(2020, 2, 10))
                .status("New")
                .build());
        mockMvc.perform(get("/brewery/brewer/requests/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : 200,\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"status\" : \"New\"\n" +
                        "  }\n"));
    }

    @Test
    public void testChangeProduceRequestStatusIsOk() throws Exception {
        mockMvc.perform(patch("/brewery/brewer/requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "  \"status\" : \"In process\"\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    void testGetRecipeIsOk() throws Exception {
        Set<Component> componentSet=new HashSet<>();
        recipeRepository.save(Recipe.builder()
                .beer_id(1L)
                .components(componentSet)
                .build());
        mockMvc.perform(get("/brewery/brewer/recipes/4"))
                .andExpect(status().isOk())
                .andExpect(content().json(" {\n" +
                        "\"id\" : 4," +
                        "\"beer_id\" : 1," +
                        "\"components\" : \"Water, Alcohol\" \n" +
                        "  }\n"));
    }

    @Test
    void testGetIngredientIsOk() throws Exception {
        ingredientRepository.save(Ingredient.builder()
                .name("Malt")
                .amount(20.5)
                .unit("kilo")
                .build());
        mockMvc.perform(get("/brewery/brewer/ingredients/5"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "  {\n" +
                                "  \"id\" : 5," +
                                "  \"name\" : \"Malt\",\n" +
                                "  \"amount\" : 20.5, \n" +
                                "  \"unit\" : \"kilo\" \n" +
                                "  }\n"));
    }

    @Test
    public void testChangeBeerAmountIsOk() throws Exception {
        beerRepository.save(Beer
                .builder()
                .name("CoolBeer")
                .type("Светлое")
                .alcohol("4,8%")
                .amount(2540)
                .recipe("Good recipe")
                .build());
        mockMvc.perform(patch("/brewery/brewer/beers/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "    \"amount\" : 2740\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("2"));
    }
}