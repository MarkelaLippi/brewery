package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.dto.OrderItemDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.OrderItem;
import gmail.roadtojob2019.brewery.mapper.OrderItemMapper;
import gmail.roadtojob2019.brewery.mapper.OrderMapper;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public Long createOrder(OrderDto orderDto) {
        final LocalDate date = orderDto.getDate();
        final Customer customer = customerRepository.getOne(orderDto.getCustomerId());
        final List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        final List<OrderItem> orderItems = orderItemDtos
                .stream()
                .map(orderItemMapper::orderItemDtoToOrderItem)
                .collect(Collectors.toList());
        final Order newOrder = Order.builder()
                .date(date)
                .customer(customer)
                .orderItems(orderItems)
                .build();
        final Order savedOrder = orderRepository.save(newOrder);
        return savedOrder.getId();
    }

    @Override
    public List<OrderDto> getAllOrders() {
        final List<Order> orders = orderRepository.findAll();
        final List<OrderDto> orderDtos = orders
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
        return orderDtos;
    }
}
