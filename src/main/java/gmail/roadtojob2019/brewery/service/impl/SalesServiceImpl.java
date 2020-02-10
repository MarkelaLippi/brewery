package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.OrderDto;
import gmail.roadtojob2019.brewery.dto.SalesSignInRequestDto;
import gmail.roadtojob2019.brewery.service.SalesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Override
    public String signIn(SalesSignInRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return List.of(OrderDto.builder()
                .id(1L)
                .date(LocalDate.of(2020,2,5))
                .name_beer("BudBeer")
                .amount(200)
                .customer_id(1L)
                .build());
    }
}

