package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "Access to orders")
@RequestMapping("/brewery")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "customer/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create order")
    public Long createOrder(@RequestBody final OrderDto orderDto) throws BrewerySuchCustomerNotFoundException {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("sales/orders")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }
}
