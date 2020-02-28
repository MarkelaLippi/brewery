package gmail.roadtojob2019.brewery.controller.unit;

import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ReviewControllerUnitTest extends AbstractControllerTest {
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
        final Optional<Customer> customer = getCustomer();
        final Optional<Order> order = getOrder(customer);
        final Review review = getReview(customer, order);
        willReturn(customer).given(customerRepository).findById(1L);
        willReturn(order).given(orderRepository).findById(1L);
        willReturn(review).given(reviewRepository).save(any(Review.class));
        String token = signInAsUser(UserRole.CUSTOMER);
        // when
        mockMvc.perform(post("/brewery/customer/reviews").header("Authorization", token)
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
        verify(customerRepository, times(1)).findById(any(Long.class));
        verify(orderRepository, times(1)).findById(any(Long.class));
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    public void testCustomerReviewThrowsBrewerySuchCustomerNotFoundException() throws Exception {
        // given
        final Optional<Customer> customer = getCustomer();
        final Optional<Order> order = getOrder(customer);
        final Review review = getReview(customer, order);
        willReturn(Optional.empty()).given(customerRepository).findById(1L);
        willReturn(order).given(orderRepository).findById(1L);
        willReturn(review).given(reviewRepository).save(any(Review.class));
        String token = signInAsUser(UserRole.CUSTOMER);
        // when
        mockMvc.perform(post("/brewery/customer/reviews").header("Authorization", token)
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-06\",\n" +
                        "  \"content\" : \"I want to thank...\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderId\" : 1\n" +
                        "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Customer with id = 1 was not found"));
        verify(customerRepository, times(1)).findById(any(Long.class));
        verify(orderRepository, times(0)).findById(any(Long.class));
        verify(reviewRepository, times(0)).save(any(Review.class));
    }

    @Test
    public void testCustomerReviewThrowsBrewerySuchOrderNotFoundException() throws Exception {
        // given
        final Optional<Customer> customer = getCustomer();
        final Optional<Order> order = getOrder(customer);
        final Review review = getReview(customer, order);
        willReturn(customer).given(customerRepository).findById(1L);
        willReturn(Optional.empty()).given(orderRepository).findById(1L);
        willReturn(review).given(reviewRepository).save(any(Review.class));
        String token = signInAsUser(UserRole.CUSTOMER);
        // when
        mockMvc.perform(post("/brewery/customer/reviews").header("Authorization", token)
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-06\",\n" +
                        "  \"content\" : \"I want to thank...\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderId\" : 1\n" +
                        "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Order with id = 1 was not found"));
        verify(customerRepository, times(1)).findById(any(Long.class));
        verify(orderRepository, times(1)).findById(any(Long.class));
        verify(reviewRepository, times(0)).save(any(Review.class));
    }

    @Test
    public void testCustomerChangeReviewIsOk() throws Exception {
        // given
        final Optional<Customer> customer = getCustomer();
        final Optional<Order> order = getOrder(customer);
        final Review review = getReview(customer, order);
        final Optional<Review> requiredReview = Optional.of(review);
        willReturn(requiredReview).given(reviewRepository).findById(any(Long.class));
        willReturn(review).given(reviewRepository).save(any(Review.class));
        String token = signInAsUser(UserRole.CUSTOMER);
        // when
        mockMvc.perform(patch("/brewery/customer/reviews/1").header("Authorization", token)
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"content\" : \"I would like to notice...\"\n" +
                        " }"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
        verify(reviewRepository, times(1)).findById(any(Long.class));
        verify(reviewRepository, times(1)).save(any(Review.class));
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

    @Test
    public void testCustomerReviewDeleteById() throws Exception {
        // given
        Long id = 1L;
        willReturn(true).given(reviewRepository).existsById(id);
        //signInAsCustomer();
        // when
        mockMvc.perform(delete("/brewery/customer/reviews/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(reviewRepository, times(1)).existsById(id);
        verify(reviewRepository, times(1)).deleteById(id);
    }

    @Test
    public void testCustomerReviewDeleteById_ReviewNotFound() throws Exception {
        // given
        Long id = 1L;
        //signInAsCustomer();
        // when
        mockMvc.perform(delete("/brewery/customer/reviews/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(reviewRepository, times(1)).existsById(id);
    }
}