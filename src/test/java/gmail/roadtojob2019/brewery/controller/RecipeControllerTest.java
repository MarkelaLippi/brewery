package gmail.roadtojob2019.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class RecipeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRecipeIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/recipes/1"))
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
}