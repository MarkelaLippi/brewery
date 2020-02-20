package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.mapper.BeerMapper;
import gmail.roadtojob2019.brewery.mapper.OrderMapper;
import gmail.roadtojob2019.brewery.mapper.ProduceRequestMapper;
import gmail.roadtojob2019.brewery.repository.BeerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ProduceRequestRepository;
import gmail.roadtojob2019.brewery.service.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final OrderRepository orderRepository;
    private final BeerRepository beerRepository;
    private final ProduceRequestRepository produceRequestRepository;

    private final OrderMapper orderMapper;
    private final BeerMapper beerMapper;
    private final ProduceRequestMapper produceRequestMapper;

}

