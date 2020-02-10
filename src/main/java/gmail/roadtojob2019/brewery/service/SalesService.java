package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.dto.SalesSignInRequestDto;

import java.util.List;

public interface SalesService {
    String signIn(SalesSignInRequestDto request);

    List<OrderDto> getAllOrders();

    List<BeerDto> getAllBeers();

    String makeRequest(ProduceRequestDto request);
}
