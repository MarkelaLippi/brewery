package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product sourceToDestination(ProductDto source);

    ProductDto destinationToSource(Product destination);
}
