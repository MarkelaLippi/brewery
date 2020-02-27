package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchProduceRequestNotFoundException;

import java.util.List;

public interface ProduceRequestService {
    Long createProduceRequest(ProduceRequestDto request);

    List<ProduceRequestDto>getProduceRequestsByStatus(String status);

    ProduceRequestDto getProduceRequest(Long id) throws BrewerySuchCustomerNotFoundException, BrewerySuchProduceRequestNotFoundException;

    Long changeProduceRequestStatus(Long id, ProduceRequestDto request);
}
