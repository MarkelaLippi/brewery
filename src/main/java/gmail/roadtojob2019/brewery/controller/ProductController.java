package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.PricelistItemDto;
import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("sales/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProductsByType(@RequestParam(value = "type") String type) {
        return productService.getAllProductsByType(type);
    }

    @PatchMapping(value = "brewer/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Long changeProductAmount(@PathVariable Long id, @RequestBody ProductDto amount) {
        return productService.changeProductAmount(id, amount);
    }
}
