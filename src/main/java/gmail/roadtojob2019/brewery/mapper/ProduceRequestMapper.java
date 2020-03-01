package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.dto.ProduceRequestItemDto;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import gmail.roadtojob2019.brewery.entity.ProduceRequestItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProduceRequestMapper {
    @Mappings({
            @Mapping(target = "produceRequestItems", source = "produceRequestItemDtos")
    })
    ProduceRequest produceRequestDtoToProduceRequest(ProduceRequestDto dto);
    @Mappings({
            @Mapping(target = "produceRequestItemDtos", source = "produceRequestItems")
    })
    ProduceRequestDto produceRequestToProduceRequestDto(ProduceRequest produceRequest);

    @Mappings({
            @Mapping(target = "product.id", source = "productId"),
            @Mapping(target = "produceRequest.id", source = "produceRequestId")
    })
    ProduceRequestItem produceRequestItemDtoToProduceRequestItem(ProduceRequestItemDto dto);

    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "produceRequestId", source = "produceRequest.id")
    })
    ProduceRequestItemDto produceRequestItemToProduceRequestItemDto(ProduceRequestItem produceRequestItem);
}
