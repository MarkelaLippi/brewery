package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.CustomerSignInRequestDto;
import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public String signUp(CustomerSignUpRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    public String signIn(CustomerSignInRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return List.of(ProductDto.builder()
                .id(1L)
                .name("BudBeer")
                .description("Dark, 4,6%...")
                .price(2.0)
                .build());
    }
}
