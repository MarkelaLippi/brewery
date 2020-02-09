package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.CustomerSignInRequestDto;
import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.service.CustomerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/brewery/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String singUp(@RequestBody CustomerSignUpRequestDto request) {
        return service.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody CustomerSignInRequestDto request) {
        return service.signIn(request);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
/*
                "[\n" +
                "  {\n" +
                "    \"id\" : 1, \n" +
                "    \"name\" : \"BudBeer\",\n" +
                "    \"description\" : \"Темное, 4,6%...\",\n" +
                "    \"price\" : \"2\" \n" +
                "  }\n" +
                "]";
*/
    }

    @PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String makeOrder(@RequestBody String request) {
        return "{\"id\":1}";
    }

    @PostMapping(value = "/review", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String makeReview(@RequestBody String request) {
        return "{\"id\":1}";
    }
}


