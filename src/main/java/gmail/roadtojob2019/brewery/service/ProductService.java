package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.PricelistItemDto;
import gmail.roadtojob2019.brewery.dto.PricelistUnitDto;

import java.util.List;

public interface ProductService {
    List<PricelistItemDto> getPricelist();
}
