package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ReviewControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    public void testCustomerReviewIsCreated() throws Exception {
        // given
        //signInAsCustomer();
        final Optional<Customer> customer = getCustomer();

        final Optional<Order> order = getOrder(customer);

        final Review review = getReview(customer, order);

        willReturn(customer).given(customerRepository).findById(1L);

        willReturn(order).given(orderRepository).findById(1L);

        willReturn(review).given(reviewRepository).save(any(Review.class));

        // when
        mockMvc.perform(post("/brewery/customer/reviews")
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-06\",\n" +
                        "  \"content\" : \"I want to thank...\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderId\" : 1\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("1"));
    }

    private Review getReview(Optional<Customer> customer, Optional<Order> order) {
        return Review.builder()
                    .id(1L)
                    .content("I want to thank...")
                    .date(LocalDate.of(2020, 2, 6))
                    .customer(customer.get())
                    .order(order.get())
                    .build();
    }

    private Optional<Order> getOrder(Optional<Customer> customer) {
        return Optional.of(Order.builder()
                    .id(1L)
                    .customer(customer.get())
                    .build());
    }

    private Optional<Customer> getCustomer() {
        return Optional.of(Customer.builder()
                    .id(1L)
                    .build());
    }
}