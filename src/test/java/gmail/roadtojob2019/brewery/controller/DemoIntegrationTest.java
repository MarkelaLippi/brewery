package gmail.roadtojob2019.brewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.repository.AuthInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

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
    @Autowired
    private AuthInfoRepository authInfoRepository;

    @Test
    public void testDemo() throws Exception {

        final Optional<AuthInfoEntity> authInfoEntity = authInfoRepository.findByLogin("Ivanov@gmail.com");
        authInfoEntity.ifPresent(infoEntity -> authInfoRepository.delete(infoEntity));

        //CustomerFlow
        customerSignUp();
        final String customerToken = userSignIn("Ivanov@gmail.com");
        getPricelist(customerToken);
        createOrder(customerToken);
        createReview(customerToken);
        changeReview(customerToken);
        deleteReview(customerToken);

        //Sales flow
        final String salesToken = userSignIn("Petrov@gmail.com");
        getOrders(salesToken);
        getBeers(salesToken);
        createProduceRequest(salesToken);

        //Brewer flow
        final String brewerToken = userSignIn("Korzun@gmail.com");
        getNewProduceRequests(brewerToken);
        getProduceRequest(brewerToken);
        changeProduceRequestStatusInProgress(brewerToken);
        getRecipe(brewerToken);
        getIngredient(brewerToken);
        changeBeerAmount(brewerToken);
        changeProduceRequestStatusCompleted(brewerToken);
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
        final String response = mockMvc.perform(post("/brewery/sign-in")
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

    private void getOrders(String salesToken) throws Exception {
        mockMvc.perform(get("/brewery/sales/orders").header("Authorization", salesToken))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "   \"id\" : 1, \n" +
                        "   \"date\" : \"05.02.2020\",\n" +
                        "   \"customerId\" : 1,\n" +
                        "   \"orderItemDtos\" : [\n" +
                        "                          {\"productId\" : 1,\n" +
                        "                           \"amount\" : 250.0 }\n" +
                        "                       ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "   \"id\" : 2, \n" +
                        "   \"date\" : \"05.02.2020\",\n" +
                        "   \"customerId\" : 1,\n" +
                        "   \"orderItemDtos\" : [\n" +
                        "                          {\"productId\" : 1,\n" +
                        "                           \"amount\" : 10.0 }\n" +
                        "                       ]\n" +
                        "  }\n" +
                        "]"));
    }

    private void getBeers(String salesToken) throws Exception {
        mockMvc.perform(get("/brewery/sales/products?type=beer").header("Authorization", salesToken))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"name\" : \"CoolBeer\",\n" +
                        "    \"description\" : \"Light, 4.8% alcohol...\",\n" +
                        "    \"price\" : 2.5,\n" +
                        "    \"amount\" : 500.0,\n" +
                        "    \"unit\" : \"LITRE\" \n" +
                        "  }\n" +
                        "]"));
    }

    private void createProduceRequest(String salesToken) throws Exception {
        mockMvc.perform(post("/brewery/sales/requests").header("Authorization", salesToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"term\" : \"2020-02-10\",\n" +
                        "  \"status\" : \"NEW\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 150 }\n" +
                        "                               ]\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("2"));
    }

    private void getNewProduceRequests(String brewerToken) throws Exception {
        mockMvc.perform(get("/brewery/brewer/requests/?status=new").header("Authorization", brewerToken))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "  \"date\" : \"02.02.2020\",\n" +
                        "  \"term\" : \"04.02.2020\",\n" +
                        "  \"status\" : \"NEW\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 350.0 }\n" +
                        "                               ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"status\" : \"NEW\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 150.0 }\n" +
                        "                               ]\n" +
                        "  }\n" +
                        "]"));
    }

    private void getProduceRequest(String brewerToken) throws Exception {
        mockMvc.perform(get("/brewery/brewer/requests/1").header("Authorization", brewerToken))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"date\" : \"02.02.2020\",\n" +
                        "  \"term\" : \"04.02.2020\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 350.0 }\n" +
                        "                               ]\n" +
                        "  }\n"));
    }

    private void changeProduceRequestStatusInProgress(String brewerToken) throws Exception {
        mockMvc.perform(patch("/brewery/brewer/requests/1").header("Authorization", brewerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "  \"status\" : \"In_progress\"\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    private void getRecipe(String brewerToken) throws Exception {
        mockMvc.perform(get("/brewery/brewer/recipes/1").header("Authorization", brewerToken))
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

    private void getIngredient(String brewerToken) throws Exception {
        mockMvc.perform(get("/brewery/brewer/products/2").header("Authorization", brewerToken))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "  {\n" +
                                "    \"id\" : 2, \n" +
                                "    \"name\" : \"Water\",\n" +
                                "    \"description\" : \"Artesian, ...\",\n" +
                                "    \"amount\" : 800.0,\n" +
                                "    \"unit\" : \"LITRE\" \n" +
                                "  }\n"));
    }

    private void changeBeerAmount(String brewerToken) throws Exception {
        mockMvc.perform(patch("/brewery/brewer/products/1").header("Authorization", brewerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "    \"amount\" : 250\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    private void changeProduceRequestStatusCompleted(String brewerToken) throws Exception {
        mockMvc.perform(patch("/brewery/brewer/requests/1").header("Authorization", brewerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "  \"status\" : \"Completed\"\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }
}

