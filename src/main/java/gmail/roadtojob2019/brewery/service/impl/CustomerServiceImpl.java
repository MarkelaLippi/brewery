package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.service.CustomerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public String makeOrder(OrderDto orderDto) {
        OrderDto.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 5))
                .name_beer("BudBeer")
                .amount(200)
                .customer_id(1L)
                .build();
        return "{\"id\":1}";
    }

    @Override
    public String makeReview(ReviewDto reviewDto) {
        ReviewDto.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 6))
                .content("Хочу поблагодарить специалиста по продажам ...")
                .customer_id(1L)
                .order_id(1L)
                .build();
        return "{\"id\":1}";
    }
}
