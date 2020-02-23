package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.OrderItemDto;
import gmail.roadtojob2019.brewery.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mappings({
            @Mapping(target = "product.id", source = "productId")
    })
    OrderItem orderItemDtoToOrderItem(OrderItemDto dto);

    @Mappings({
            @Mapping(target = "productId", source = "product.id")
    })
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);
}
