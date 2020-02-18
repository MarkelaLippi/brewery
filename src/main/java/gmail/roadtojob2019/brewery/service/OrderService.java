package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();
}
