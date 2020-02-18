package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.entity.Beer;
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

    @PostConstruct
    public void init() {
/*
        beerRepository.save(Beer
                .builder()
                .name("CoolBeer")
                .description("Light, 4.8% alcohol...")
                .price(2.5)
                .amount(2540)
                .unit("litre")
                .build());
*/

        customerRepository.save(Customer.builder()
                .fullName("Ivanov Ivan")
                .email("Ivanov@gmail.com")
                .phone("+375297772255")
                .build());
    }

    @Override
    public Long createOrder(OrderDto orderDto) {
        final Order newOrder = Order.builder()
                .date(orderDto.getDate())
                .amount(orderDto.getAmount())
                .unit(orderDto.getUnit())
                .beer(beerRepository.getOne(orderDto.getBeerId()))
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
