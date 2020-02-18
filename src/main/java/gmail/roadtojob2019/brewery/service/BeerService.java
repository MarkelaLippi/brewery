package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.PricelistUnitDto;

import java.util.List;

public interface BeerService {
    List<PricelistUnitDto> getPricelist();

    List<BeerDto> getAllBeers();
}
