package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.PricelistItemDto;
import gmail.roadtojob2019.brewery.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PricelistMapper {
    @Mappings({
            @Mapping(target = "beerId", source = "id"),
            @Mapping(target = "beerName", source = "name"),
            @Mapping(target = "beerDescription", source = "description")
    })
    PricelistItemDto productToPricelistItemDto(Product product);
}
