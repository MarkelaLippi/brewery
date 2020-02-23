package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.dto.OrderItemDto;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mappings({
            @Mapping(target = "customer.id", source = "customerId"),
            @Mapping(target = "orderItems", source = "orderItemDtos")
    })
    Order orderDtoToOrder(OrderDto dto);

    @Mappings({
            @Mapping(target = "customerId", source = "customer.id"),
            @Mapping(target = "orderItemDtos", source = "orderItems")
    })
    OrderDto orderToOrderDto(Order order);

    @Mappings({
            @Mapping(target = "product.id", source = "productId")
    })
    OrderItem orderItemDtoToOrderItem(OrderItemDto dto);

    @Mappings({
            @Mapping(target = "productId", source = "product.id")
    })
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);

    List<OrderItem> convertOrderItemDtoListToOrderItemList(List<OrderItemDto> orderItemDtoList);

    List<OrderItemDto> convertOrderItemListToOrderItemDtoList(List<OrderItem> orderItemList);
}
