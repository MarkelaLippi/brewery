package gmail.roadtojob2019.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSalesSignInIsOk() throws Exception {
        mockMvc.perform(post("/brewery/sales/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Petrov@gmail.com\",\n" +
                        "  \"password\" : \"87654321\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    void testGetAllOrdersIsOk() throws Exception {
        mockMvc.perform(get("/brewery/sales/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"date\" : \"05.02.2020\",\n" +
                        "    \"name_beer\" : \"BudBeer\",\n" +
                        "    \"amount\" : \"200\",\n" +
                        "    \"customer_id\" : \"1\" \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    void testGetAllBeersIsOk() throws Exception {
        mockMvc.perform(get("/brewery/sales/beers"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"name\" : \"CoolBeer\",\n" +
                        "    \"type\" : \"Светлое\",\n" +
                        "    \"alcohol\" : \"4,8%\",\n" +
                        "    \"amount\" : \"2540\",\n" +
                        "    \"recipe\" : \"water, spirit…\" \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testSalesProduceRequestIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/sales/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : \"200\",\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"status\" : \"new\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }
}