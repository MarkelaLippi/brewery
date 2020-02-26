package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderDto orderDto) throws BrewerySuchCustomerNotFoundException;

    List<OrderDto> getAllOrders();
}
