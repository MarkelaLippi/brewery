package gmail.roadtojob2019.brewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class DemoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDemo() throws Exception {

        //CustomerFlow
        customerSignUp();
        final String customerToken = userSignIn("Ivanov@gmail.com");
        getPricelist(customerToken);
        createOrder(customerToken);
        createReview(customerToken);
        changeReview(customerToken);
        deleteReview(customerToken);

    }

    private void customerSignUp() throws Exception {
        mockMvc.perform(post("/brewery/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\", \n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"fullName\" : \"Ivanov Ivan\" \n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(145)));
    }

    private String userSignIn(final String email) throws Exception {
        final String response=mockMvc.perform(post("/brewery/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"" + email + "\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(145)))
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + objectMapper.readValue(response, UserSignInResponseDto.class).getToken();
    }

    private void getPricelist(String customerToken) throws Exception {
        mockMvc.perform(get("/brewery/customer/pricelist").header("Authorization", customerToken))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"beerId\" : 1, \n" +
                        "    \"beerName\" : \"CoolBeer\",\n" +
                        "    \"beerDescription\" : \"Light, 4.8% alcohol...\",\n" +
                        "    \"price\" : 2.5\n" +
                        "  }\n" +
                        "]"));
    }

    private void createOrder(String customerToken) throws Exception {
        mockMvc.perform(post("/brewery/customer/orders").header("Authorization", customerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderItemDtos\" : [\n" +
                        "                          {\"productId\" : 1,\n" +
                        "                           \"amount\" : 10 }\n" +
                        "                      ]\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("2"));
    }

    private void createReview(String customerToken) throws Exception {
        mockMvc.perform(post("/brewery/customer/reviews").header("Authorization", customerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-06\",\n" +
                        "  \"content\" : \"I want to thank...\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderId\" : 1\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("3"));
    }

    private void changeReview(String customerToken) throws Exception {
        mockMvc.perform(patch("/brewery/customer/reviews/1").header("Authorization", customerToken)
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"content\" : \"I would like to notice...\"\n" +
                        " }"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    private void deleteReview(String customerToken) throws Exception {
        mockMvc.perform(delete("/brewery/customer/reviews/3").header("Authorization", customerToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

