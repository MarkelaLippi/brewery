package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.PricelistUnitDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.mapper.PricelistMapper;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final PricelistMapper pricelistMapper;

    @PostConstruct
    public void init() {
        beerRepository.save(Beer
                .builder()
                .name("CoolBeer")
                .description("Light, 4.8% alcohol...")
                .price(2.5)
                .amount(2540)
                .unit("litre")
                .build());
    }

    @Override
    public List<PricelistUnitDto> getPricelist() {
        return beerRepository
                .findAll()
                .stream()
                .map(pricelistMapper::beerToPriceListUnitDto)
                .collect(Collectors.toList());
    }
}
