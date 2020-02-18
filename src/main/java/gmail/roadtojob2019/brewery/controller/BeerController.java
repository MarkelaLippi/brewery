package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.PricelistUnitDto;
import gmail.roadtojob2019.brewery.service.BeerService;
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
public class BeerController {

    private final BeerService beerService;

    @GetMapping("customer/pricelist")
    @ResponseStatus(HttpStatus.OK)
    public List<PricelistUnitDto> getPricelist() {
        return beerService.getPricelist();
    }

    @GetMapping("sales/beers")
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDto> getAllBeers() {
        return beerService.getAllBeers();
    }

}
