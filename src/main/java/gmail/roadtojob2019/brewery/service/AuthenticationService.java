package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.dto.SignInRequestDto;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerAlreadyExistException;

public interface AuthenticationService {

    void signUp(CustomerSignUpRequestDto request) throws BrewerySuchCustomerAlreadyExistException;

    UserSignInResponseDto singIn(SignInRequestDto request);
}
