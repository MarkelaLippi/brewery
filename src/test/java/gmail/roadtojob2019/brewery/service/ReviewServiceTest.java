package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.mapper.ReviewMapper;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import gmail.roadtojob2019.brewery.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ReviewServiceTest {

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @Test
    void testCreateReview() {
        ReviewDto reviewDto = ReviewDto.builder().customerId(1L).orderId(1L).build();
        Review newReview = Review.builder().id(1L).build();
        Long customerId = reviewDto.getCustomerId();
        Long orderId = reviewDto.getOrderId();
        Optional<Customer> customer = Optional.of(Customer.builder().id(customerId).build());
        Optional<Order> order = Optional.of(Order.builder().id(orderId).build());

        willReturn(newReview).given(reviewMapper)
                .reviewDtoToReview(reviewDto);

        willReturn(customer).given(customerRepository)
                .findById(customerId);

        willReturn(order).given(orderRepository)
                .findById(orderId);

        willReturn(newReview).given(reviewRepository)
                .save(any(Review.class));

        Long id = reviewService.createReview(reviewDto);

        assertEquals(newReview.getId(), id);
    }
}