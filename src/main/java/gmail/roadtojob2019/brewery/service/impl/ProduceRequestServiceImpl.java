package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.dto.ProduceRequestItemDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import gmail.roadtojob2019.brewery.entity.ProduceRequestItem;
import gmail.roadtojob2019.brewery.entity.Status;
import gmail.roadtojob2019.brewery.mapper.ProduceRequestItemMapper;
import gmail.roadtojob2019.brewery.mapper.ProduceRequestMapper;
import gmail.roadtojob2019.brewery.repository.ProduceRequestRepository;
import gmail.roadtojob2019.brewery.service.ProduceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProduceRequestServiceImpl implements ProduceRequestService {

    private final ProduceRequestRepository produceRequestRepository;
    private final ProduceRequestMapper produceRequestMapper;
    private final ProduceRequestItemMapper produceRequestItemMapper;


    @Override
    public Long createProduceRequest(ProduceRequestDto request) {
        final ProduceRequest newProduceRequest = produceRequestMapper.produceRequestDtoToProduceRequest(request);
        final ProduceRequest savedProduceRequest = produceRequestRepository.save(newProduceRequest);
        return savedProduceRequest.getId();
    }

    @Override
    public List<ProduceRequestDto> getProduceRequestsByStatus(String status) {
        Status requiredStatus = Status.valueOf(status.toUpperCase());
        List<ProduceRequest> produceRequests = produceRequestRepository.findByStatus(requiredStatus);
        List<ProduceRequestDto> produceRequestDtos = produceRequests
                .stream()
                .map(produceRequestMapper::produceRequestToProduceRequestDto)
                .collect(Collectors.toList());
        return produceRequestDtos;
    }

    @Override
    public ProduceRequestDto getProduceRequest(Long id) {
        return produceRequestMapper.produceRequestToProduceRequestDto(produceRequestRepository.findById(id).get());
    }

    @Override
    public Long changeProduceRequestStatus(Long id, ProduceRequestDto produceRequestDto) {
        Status requiredStatus = Status.valueOf(produceRequestDto.getStatus().toUpperCase());
        ProduceRequest produceRequestBeforeChangingStatus = produceRequestRepository.findById(id).get();
        produceRequestBeforeChangingStatus.setStatus(requiredStatus);
        ProduceRequest produceRequestAfterChangingStatus = produceRequestRepository.save(produceRequestBeforeChangingStatus);
        return produceRequestAfterChangingStatus.getId();
    }

}

