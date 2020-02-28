package gmail.roadtojob2019.brewery.controller.integration;

import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCustomerReviewIsCreated() throws Exception {
        // given
        //signInAsCustomer();
        // when
        mockMvc.perform(post("/brewery/customer/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-06\",\n" +
                        "  \"content\" : \"I want to thank...\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderId\" : 1\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("2"));
    }

    @Test
    public void testCustomerChangeReviewIsOk() throws Exception {
        // given
        //signInAsCustomer();
        // when
        mockMvc.perform(patch("/brewery/customer/reviews/1")
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"content\" : \"I would like to notice...\"\n" +
                        " }"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }
}