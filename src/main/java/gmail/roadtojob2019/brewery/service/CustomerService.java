package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerAlreadyExistException;

public interface CustomerService {

    void signUp(CustomerSignUpRequestDto request) throws BrewerySuchCustomerAlreadyExistException;

}

