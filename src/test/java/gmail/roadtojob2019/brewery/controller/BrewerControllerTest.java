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
                        "  \"amount\" : 200,\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"status\" : \"New\"\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testChangeProduceRequestStatusIsOk() throws Exception {
        mockMvc.perform(put("/brewery/brewer/requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : 200,\n" +
                        "  \"term\" : \"2020-02-10\",\n" +
                        "  \"status\" : \"In process\"\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    void testGetRecipeIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(" {" +
                        "\"id\" : 1," +
                        "\"beer_id\" : 1," +
                        "\"components\" : {\"Water\" : 2.5," +
                        "                  \"Alcohol\" : 0.5 }\n" +
                        "  }\n"));
    }

    @Test
    void testGetIngredientIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "  {\n" +
                                "  \"id\" : 1," +
                                "  \"name\" : \"Malt\",\n" +
                                "  \"amount\" : 20.5\n" +
                                "  }\n"));
    }

    @Test
    public void testChangeBeerAmountIsOk() throws Exception {
        mockMvc.perform(put("/brewery/brewer/beers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"name\" : \"CoolBeer\",\n" +
                        "    \"type\" : \"Светлое\",\n" +
                        "    \"alcohol\" : \"4,8%\",\n" +
                        "    \"amount\" : 2740,\n" +
                        "    \"recipe\" : {\"id\" : 1," +
                        "                  \"beer_id\" : 1," +
                        "                  \"components\" : {\"Water\" : 2.5," +
                        "                                    \"Alcohol\" : 0.5 }\n" +
                        "  }\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }
}