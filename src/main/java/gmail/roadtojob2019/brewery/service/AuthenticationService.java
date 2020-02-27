package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.SignInRequestDto;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;

public interface AuthenticationService {

    UserSignInResponseDto singIn(SignInRequestDto request);
}
