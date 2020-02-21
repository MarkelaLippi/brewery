package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mappings({
            //@Mapping(target = "beer.id", source = "beerId"),
            @Mapping(target = "customer.id", source = "customerId")
    })
    Order orderDtoToOrder(OrderDto dto);

    @Mappings({
            //@Mapping(target = "beerId", source = "beer.id"),
            @Mapping(target = "customerId", source = "customer.id")
    })
    OrderDto orderToOrderDto(Order order);
}
