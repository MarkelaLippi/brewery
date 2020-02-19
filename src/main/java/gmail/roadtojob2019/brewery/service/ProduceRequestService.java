package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;

import java.util.List;

public interface ProduceRequestService {
    Long createProduceRequest(ProduceRequestDto request);

    List<ProduceRequestDto>getProduceRequestsByStatus(String status);

    ProduceRequestDto getProduceRequest(Long id);

    Long changeProduceRequestStatus(Long id, ProduceRequestDto request);
}
