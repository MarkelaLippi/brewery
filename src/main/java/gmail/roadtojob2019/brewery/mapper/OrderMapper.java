package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderDtoToOrder(OrderDto dto);

    OrderDto orderToOrderDto(Order order);
}
