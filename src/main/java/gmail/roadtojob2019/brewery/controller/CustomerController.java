package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.exception.SuchCustomerAlreadyExistException;
import gmail.roadtojob2019.brewery.security.JwtUtil;
import gmail.roadtojob2019.brewery.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brewery/customer")
public class CustomerController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private CustomerService service;


    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponseDto singUp(@RequestBody final CustomerSignUpRequestDto request)
            throws SuchCustomerAlreadyExistException {
        service.signUp(request);
        return singIn(new CustomerSignInRequestDto(request.getEmail(), request.getPassword()));
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserSignInResponseDto singIn(@RequestBody final CustomerSignInRequestDto request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new UserSignInResponseDto(
                jwtUtil.generateToken(
                        new User(request.getEmail(), request.getPassword(),
                                List.of(new SimpleGrantedAuthority("CUSTOMER")))));
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createOrder(@RequestBody OrderDto orderDto) {
        return service.createOrder(orderDto);
    }

    @PostMapping(value = "/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createReview(@RequestBody ReviewDto reviewDto) {
        return service.createReview(reviewDto);
    }
}


