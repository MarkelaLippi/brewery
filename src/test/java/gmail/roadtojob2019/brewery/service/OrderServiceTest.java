package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.mapper.OrderMapper;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderMapper orderMapper;

    @Test
    void testCreateOrder() {
        OrderDto orderDto = OrderDto.builder().customerId(1L).build();
        Order newOrder = Order.builder().id(1L).build();
        Long customerId = orderDto.getCustomerId();
        Customer customer = Customer.builder().id(1L).build();

        willReturn(newOrder).given(orderMapper)
                .orderDtoToOrder(orderDto);

        willReturn(customer).given(customerRepository)
                .getOne(customerId);

        willReturn(newOrder).given(orderRepository)
                .save(any(Order.class));

        Long id = orderService.createOrder(orderDto);
        assertEquals(newOrder.getId(), id);
    }

    @Test
    void testGetAllOrders() {
        Order order = Order.builder().id(1L).build();
        List<Order> orders = List.of(order);

        willReturn(orders).given(orderRepository)
                .findAll();

        List<OrderDto> orderDtos = orderService.getAllOrders();

        assertEquals(orders.size(), orderDtos.size());
    }
}