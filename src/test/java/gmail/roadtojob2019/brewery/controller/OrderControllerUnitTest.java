package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.OrderItem;
import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OrderControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testCustomerOrderIsCreated() throws Exception {
        // given
        // signInAsCustomer();
        final Optional<Customer> customer = getCustomer();

        final Order order = getOrder(customer);

        willReturn(order).given(orderRepository).save(any(Order.class));

        willReturn(customer).given(customerRepository).findById(1L);

        // when
        mockMvc.perform(post("/brewery/customer/orders")
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
        // signInAsCustomer();
        final List<Order> orders = getOrders();

        willReturn(orders).given(orderRepository)
                .findAll();
        // when
        mockMvc.perform(get("/brewery/sales/orders"))
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