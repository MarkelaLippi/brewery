package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
/*
    @MockBean
    private ReviewRepository reviewRepository;
*/

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
}