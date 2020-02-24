package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.exception.SuchCustomerAlreadyExistException;

public interface CustomerService {

    void signUp(CustomerSignUpRequestDto request) throws SuchCustomerAlreadyExistException;

}

