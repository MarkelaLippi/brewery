package gmail.roadtojob2019.brewery.controller.unit;

import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.OrderItem;
import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class OrderControllerUnitTest extends AbstractControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testCustomerOrderIsCreated() throws Exception {
        // given
        final Optional<Customer> customer = getCustomer();
        final Order order = getOrder(customer);
        willReturn(customer).given(customerRepository).findById(1L);
        willReturn(order).given(orderRepository).save(any(Order.class));
        String token = signInAsUser(UserRole.CUSTOMER);
        // when
        mockMvc.perform(post("/brewery/customer/orders").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderItemDtos\" : [\n" +
                        "                          {\"productId\" : 1,\n" +
                        "                           \"amount\" : 10 }\n" +
                        "                      ]\n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("2"));
        verify(customerRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testCustomerOrderThrowsBrewerySuchCustomerNotFoundException() throws Exception {
        // given
        final Optional<Customer> customer = getCustomer();
        final Order order = getOrder(customer);
        willReturn(Optional.empty()).given(customerRepository).findById(1L);
        willReturn(order).given(orderRepository).save(any(Order.class));
        String token = signInAsUser(UserRole.CUSTOMER);
        // when
        mockMvc.perform(post("/brewery/customer/orders").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"customerId\" : 1,\n" +
                        "  \"orderItemDtos\" : [\n" +
                        "                          {\"productId\" : 1,\n" +
                        "                           \"amount\" : 10 }\n" +
                        "                      ]\n" +
                        "}"))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Customer with id = 1 was not found"));
        verify(customerRepository, times(1)).findById(1L);
        verify(orderRepository, times(0)).save(any(Order.class));
    }

    private Optional<Customer> getCustomer() {
        return Optional.of(Customer.builder()
                .id(1L)
                .build());
    }

    private Order getOrder(Optional<Customer> customer) {
        return Order.builder()
                .id(2L)
                .customer(customer.get())
                .build();
    }

    @Test
    void testGetAllOrdersIsOk() throws Exception {
        // given
        final List<Order> orders = getOrders();
        willReturn(orders).given(orderRepository).findAll();
        String token = signInAsUser(UserRole.SALES);
        // when
        mockMvc.perform(get("/brewery/sales/orders").header("Authorization", token))
                // then
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
                        "  }\n" +
                        "]"));
        verify(orderRepository, times(1)).findAll();
    }

    private List<Order> getOrders() {
        final OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .amount(250.0)
                .product(Product.builder().id(1L).build())
                .build();

        final Order order = Order.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 5))
                .customer(Customer.builder().id(1L).build())
                .orderItems(List.of(orderItem))
                .build();

        return List.of(order);
    }
}