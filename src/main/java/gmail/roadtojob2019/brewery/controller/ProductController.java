package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.PricelistItemDto;
import gmail.roadtojob2019.brewery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brewery")
public class ProductController {

    private ProductService productService;

    @GetMapping("customer/pricelist")
    @ResponseStatus(HttpStatus.OK)
    public List<PricelistItemDto> getPricelist() {
        return productService.getPricelist();
    }
}
