package gmail.roadtojob2019.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BrewerControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    void testGetAllProduceRequestIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/requests"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : \"200\",\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"status\" : \"new\"\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testChangeProduceRequestStatusIsOk() throws Exception {
        mockMvc.perform(put("/brewery/brewer/requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"status\" : \"in process\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }


    @Test
    void testGetRecipeIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "  \"water\" : \"300\",\n" +
                        "  \"alcohol\" : \"15\",\n" +
                        "  \"malt\" : \"10\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "  \"beer_id\" : \"1\"\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    void testGetIngredientIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "  {\n" +
                                "  \"name\" : \"malt\",\n" +
                                "  \"amount\" : \"80\"\n" +
                                "  }\n"
                ));
    }

    @Test
    public void testChangeBeerAmountIsOk() throws Exception {
        mockMvc.perform(put("/brewery/brewer/beers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"amount\" : \"500\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }
}