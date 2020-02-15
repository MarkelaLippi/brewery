package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BeerRepository beerRepository;

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
        orderRepository.save(Order.builder()
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .customer_id(1L)
                .build());
        mockMvc.perform(get("/brewery/sales/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"date\" : \"05.02.2020\",\n" +
                        "    \"name_beer\" : \"BudBeer\",\n" +
                        "    \"amount\" : 200,\n" +
                        "    \"customer_id\" : 1 \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    void testGetAllBeersIsOk() throws Exception {
        beerRepository.save(Beer
                .builder()
                .name("CoolBeer")
                .type("Светлое")
                .alcohol("4,8%")
                .amount(2540)
                .recipe("Good recipe")
                .build());
        mockMvc.perform(get("/brewery/sales/beers"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 2, \n" +
                        "    \"name\" : \"CoolBeer\",\n" +
                        "    \"type\" : \"Светлое\",\n" +
                        "    \"alcohol\" : \"4,8%\",\n" +
                        "    \"amount\" : 2540,\n" +
                        "    \"recipe\" : \"Good recipe\" \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testSalesProduceRequestIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/sales/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : 200,\n" +
                        "  \"term\" : \"2020-02-10\",\n" +
                        "  \"status\" : \"New\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("3"));
    }
}