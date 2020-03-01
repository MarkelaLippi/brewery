package gmail.roadtojob2019.brewery.controller.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPriselistIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/brewery/customer/pricelist"))
                //then
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

    @Test
    void testGetAllProductsByTypeIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/brewery/sales/products?type=beer"))
                //then
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

    @Test
    void testGetProductByIdIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/brewery/brewer/products/2"))
                //then
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

    @Test
    void testGetProductsByIdsIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/brewery/brewer/products?ids=2,3"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                                "  {\n" +
                                "    \"id\" : 2, \n" +
                                "    \"name\" : \"Water\",\n" +
                                "    \"description\" : \"Artesian, ...\",\n" +
                                "    \"amount\" : 800.0,\n" +
                                "    \"unit\" : \"LITRE\" \n" +
                                "  },\n"+
                                "  {\n" +
                                "    \"id\" : 3, \n" +
                                "    \"name\" : \"Alcohol\",\n" +
                                "    \"description\" : \"Concentration 90%, ...\",\n" +
                                "    \"amount\" : 100.0,\n" +
                                "    \"unit\" : \"LITRE\" \n" +
                                "  }\n"+
                              "]"));
    }

    @Test
    public void testChangeProductAmountIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(patch("/brewery/brewer/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "    \"amount\" : 250\n" +
                        "  }\n"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }
}