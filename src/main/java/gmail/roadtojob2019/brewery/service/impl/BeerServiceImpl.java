package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.PricelistUnitDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.mapper.BeerMapper;
import gmail.roadtojob2019.brewery.mapper.PricelistMapper;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final PricelistMapper pricelistMapper;
    private final BeerMapper beerMapper;

/*
    @Override
    public List<PricelistUnitDto> getPricelist() {
        return beerRepository
                .findAll()
                .stream()
                .map(pricelistMapper::productToPricelistItemDto)
                .collect(Collectors.toList());
    }
*/

    @Override
    public List<BeerDto> getAllBeers() {
        return beerRepository
                .findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long changeBeerAmount(Long id, BeerDto beerDto) {
        Beer beerBefore = beerRepository.getOne(id);
        beerBefore.setAmount(beerDto.getAmount());
        Beer beerAfter = beerRepository.save(beerBefore);
        return beerAfter.getId();
    }
}
