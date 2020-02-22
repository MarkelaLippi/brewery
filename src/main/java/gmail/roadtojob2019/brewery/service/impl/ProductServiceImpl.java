package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.PricelistItemDto;
import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.entity.Type;
import gmail.roadtojob2019.brewery.mapper.PricelistMapper;
import gmail.roadtojob2019.brewery.mapper.ProductMapper;
import gmail.roadtojob2019.brewery.repository.ProductRepository;
import gmail.roadtojob2019.brewery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static gmail.roadtojob2019.brewery.entity.Type.BEER;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private PricelistMapper pricelistMapper;
    private ProductMapper productMapper;

    @Override
    public List<PricelistItemDto> getPricelist() {
        return productRepository
                .findByType(BEER)
                .stream()
                .map(pricelistMapper::productToPricelistItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductsByType(String type) {
        Type requiredType = Type.valueOf(type.toUpperCase());
        return productRepository
                .findByType(requiredType)
                .stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }
}
