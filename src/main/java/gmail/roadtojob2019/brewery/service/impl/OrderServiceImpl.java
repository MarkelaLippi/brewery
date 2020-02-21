package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.mapper.OrderMapper;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    private final OrderMapper orderMapper;

    @Override
    public Long createOrder(OrderDto orderDto) {
        final Order newOrder = Order.builder()
                .date(orderDto.getDate())
                .customer(customerRepository.getOne(orderDto.getCustomerId()))
                .build();
        return orderRepository.save(newOrder).getId();
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }
}
