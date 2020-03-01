package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.dto.SignInRequestDto;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerAlreadyExistException;
import gmail.roadtojob2019.brewery.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "User authentication system")
@RequestMapping("/brewery")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Sign-up customer")
    public UserSignInResponseDto singUp(@RequestBody final CustomerSignUpRequestDto signUpRequest)
            throws BrewerySuchCustomerAlreadyExistException {
        final UserSignInResponseDto userSignInResponseDto = authenticationService.signUp(signUpRequest);
        return userSignInResponseDto;
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Sign-in user")
    public UserSignInResponseDto singIn(@RequestBody final SignInRequestDto request) {
        final UserSignInResponseDto userSignInResponseDto = authenticationService.singIn(request);
        return userSignInResponseDto;
    }
}

