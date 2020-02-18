package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.dto.SalesSignInRequestDto;
import gmail.roadtojob2019.brewery.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brewery/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody SalesSignInRequestDto request) {
        return salesService.signIn(request);
    }



/*
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return salesService.getAllOrders();
    }
*/

    @GetMapping("/beers")
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDto> getAllBeers() {
        return salesService.getAllBeers();
    }

    @PostMapping(value = "/requests", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduceRequest(@RequestBody ProduceRequestDto request) {
        return salesService.createProduceRequest(request);
    }
}
