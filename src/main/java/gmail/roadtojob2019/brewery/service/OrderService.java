package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.OrderDto;

public interface OrderService {
    Long createOrder(OrderDto orderDto);
}
