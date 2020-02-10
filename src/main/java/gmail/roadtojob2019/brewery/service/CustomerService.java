package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.*;

import java.util.List;

public interface CustomerService {

    String signUp(CustomerSignUpRequestDto request);

    String signIn(CustomerSignInRequestDto request);

    List<ProductDto> getAllProducts();

    String makeOrder(OrderDto orderDto);

    String makeReview(ReviewDto reviewDto);
}

