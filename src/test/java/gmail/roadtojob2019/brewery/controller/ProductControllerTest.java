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
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPriselistIsOk() throws Exception {
        // given
        //signInAsCustomer();
        // when
        mockMvc.perform(get("/brewery/customer/pricelist"))
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
}