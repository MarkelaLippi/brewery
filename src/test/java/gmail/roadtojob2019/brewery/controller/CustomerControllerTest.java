package gmail.roadtojob2019.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/customer/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"login\" : \"Ivanov\",\n" +
                        "  \"password\" : \"12345678\" \n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testCustomerSignInIsOk() throws Exception {
        mockMvc.perform(post("/brewery/customer/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    void testGetAllProductsIsOk() throws Exception {
        mockMvc.perform(get("/brewery/customer/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"name\" : \"BudBeer\",\n" +
                        "    \"description\" : \"Dark, 4,6%...\",\n" +
                        "    \"price\" : 2.0 \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testCustomerOrderIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/customer/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : 200,\n" +
                        "  \"customer_id\" : 1 \n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testCustomerReviewIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/customer/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"06.02.2020\",\n" +
                        "  \"content\" : \"Хочу поблагодарить специалиста по продажам ...\",\n" +
                        "  \"customer_id\" : \"1\",\n" +
                        "  \"order_id\" : \"1\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }
}

