package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.mapper.OrderMapper;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public Long createOrder(OrderDto orderDto) {
        Order newOrder = orderMapper.orderDtoToOrder(orderDto);
        Customer customer = customerRepository.findById(orderDto.getCustomerId()).get();
        newOrder.setCustomer(customer);
        Order savedOrder = orderRepository.save(newOrder);
        return savedOrder.getId();
    }

    @Override
    @Transactional
    public List<OrderDto> getAllOrders() {
        final List<Order> orders = orderRepository.findAll();
        final List<OrderDto> orderDtos = orders
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
        return orderDtos;
    }
}
