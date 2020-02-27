package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.PricelistItemDto;
import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<PricelistItemDto> getPricelist();

    List<ProductDto> getAllProductsByType(String type);

    Long changeProductAmount(Long id, ProductDto amount);

    ProductDto getProductById(Long id) throws BrewerySuchProductNotFoundException;
}
