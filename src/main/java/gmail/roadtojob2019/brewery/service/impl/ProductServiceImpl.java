package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.PricelistItemDto;
import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.entity.Storage;
import gmail.roadtojob2019.brewery.entity.Type;
import gmail.roadtojob2019.brewery.mapper.PricelistMapper;
import gmail.roadtojob2019.brewery.mapper.ProductMapper;
import gmail.roadtojob2019.brewery.repository.ProductRepository;
import gmail.roadtojob2019.brewery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public List<PricelistItemDto> getPricelist() {
        List<Product> products = productRepository
                .findByType(BEER);
        List<PricelistItemDto> pricelistItemDtos = products
                .stream()
                .map(pricelistMapper::productToPricelistItemDto)
                .collect(Collectors.toList());
        return pricelistItemDtos;
    }

    @Override
    @Transactional
    public List<ProductDto> getAllProductsByType(String type) {
        Type requiredType = Type.valueOf(type.toUpperCase());
        List<Product> products = productRepository
                .findByType(requiredType);
        List<ProductDto> productDtos = products
                .stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    @Transactional
    public Long changeProductAmount(Long id, ProductDto productDto) {
        Product productBeforeChangingAmount = productRepository.findById(id).get();
        Storage storage = productBeforeChangingAmount.getStorage();
        final Double amountBeforeChanging = storage.getAmount();
        final Double createdProductAmount = productDto.getAmount();
        final Double amountAfterChanging=amountBeforeChanging+createdProductAmount;
        storage.setAmount(amountAfterChanging);
        productBeforeChangingAmount.setStorage(storage);
        final Product productAfterChangingAmount = productRepository.save(productBeforeChangingAmount);
        return productAfterChangingAmount.getId();
    }

    @Override
    @Transactional
    public ProductDto getProductById(Long id) {
        final Product product = productRepository.findById(id).get();
        final ProductDto productDto = productMapper.productToProductDto(product);
        return productDto;
    }
}
