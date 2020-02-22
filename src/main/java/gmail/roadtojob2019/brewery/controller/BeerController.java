package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.PricelistUnitDto;
import gmail.roadtojob2019.brewery.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brewery")
public class BeerController {

    private final BeerService beerService;

/*
    @GetMapping("customer/pricelist")
    @ResponseStatus(HttpStatus.OK)
    public List<PricelistUnitDto> getPricelist() {
        return beerService.getPricelist();
    }
*/

    @GetMapping("sales/beers")
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDto> getAllBeers() {
        return beerService.getAllBeers();
    }

    @PatchMapping(value = "brewer/beers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Long changeBeerAmount(@PathVariable Long id, @RequestBody BeerDto beer) {
        return beerService.changeBeerAmount(id, beer);
    }

}
