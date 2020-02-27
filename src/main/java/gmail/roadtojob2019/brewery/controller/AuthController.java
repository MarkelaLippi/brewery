package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.SignInRequestDto;
import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerAlreadyExistException;
import gmail.roadtojob2019.brewery.security.JwtUtil;
import gmail.roadtojob2019.brewery.service.CustomerService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@Api
@RequestMapping("/brewery")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final CustomerService customerService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponseDto singUp(@RequestBody final CustomerSignUpRequestDto request)
            throws BrewerySuchCustomerAlreadyExistException {
        customerService.signUp(request);
        return singIn(new SignInRequestDto(request.getEmail(), request.getPassword()));
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserSignInResponseDto singIn(@RequestBody final SignInRequestDto request) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        final UserDetails principal = (UserDetails)authentication.getPrincipal();
        final Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

        final String ROLE = authorities
                .stream()
                .findFirst()
                .get()
                .getAuthority()
                .substring(5);

        User user = new User(request.getEmail(), request.getPassword(), List.of(new SimpleGrantedAuthority(ROLE)));
        String token = jwtUtil.generateToken(user);
        UserSignInResponseDto userSignInResponseDto = new UserSignInResponseDto(token);
        return userSignInResponseDto;
    }
}


