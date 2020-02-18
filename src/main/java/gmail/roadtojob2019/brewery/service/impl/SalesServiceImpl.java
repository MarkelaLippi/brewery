package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.dto.SalesSignInRequestDto;
import gmail.roadtojob2019.brewery.mapper.BeerMapper;
import gmail.roadtojob2019.brewery.mapper.OrderMapper;
import gmail.roadtojob2019.brewery.mapper.ProduceRequestMapper;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ProduceRequestRepository;
import gmail.roadtojob2019.brewery.service.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final OrderRepository orderRepository;
    private final BeerRepository beerRepository;
    private final ProduceRequestRepository produceRequestRepository;

    private final OrderMapper orderMapper;
    private final BeerMapper beerMapper;
    private final ProduceRequestMapper produceRequestMapper;

    @Override
    public String signIn(SalesSignInRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeerDto> getAllBeers() {
        List<BeerDto> collect = beerRepository
                .findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

    @Override
    public Long createProduceRequest(ProduceRequestDto request) {
        return produceRequestRepository
                .save(produceRequestMapper.produceRequestDtoToProduceRequest(request))
                .getId();
    }
}

