package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.CustomerSignInRequestDto;
import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.dto.ProductDto;

import java.util.List;

public interface CustomerService {

    String signUp(CustomerSignUpRequestDto request);

    String signIn(CustomerSignInRequestDto request);

    List<ProductDto> getAllProducts();

}

