package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.service.OrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Api
@RequestMapping("/brewery")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "customer/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createOrder(@RequestBody OrderDto orderDto) throws BrewerySuchCustomerNotFoundException {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("sales/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }
}
