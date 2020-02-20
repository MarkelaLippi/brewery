package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.exception.SuchCustomerAlreadyExistException;

import java.util.List;

public interface CustomerService {

    void signUp(CustomerSignUpRequestDto request) throws SuchCustomerAlreadyExistException;

}

