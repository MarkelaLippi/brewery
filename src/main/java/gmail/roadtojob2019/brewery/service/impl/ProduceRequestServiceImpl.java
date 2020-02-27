package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import gmail.roadtojob2019.brewery.entity.Status;
import gmail.roadtojob2019.brewery.exception.BrewerySuchProduceRequestNotFoundException;
import gmail.roadtojob2019.brewery.mapper.ProduceRequestMapper;
import gmail.roadtojob2019.brewery.repository.ProduceRequestRepository;
import gmail.roadtojob2019.brewery.service.ProduceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProduceRequestServiceImpl implements ProduceRequestService {

    private final ProduceRequestRepository produceRequestRepository;
    private final ProduceRequestMapper produceRequestMapper;

    @Override
    @Transactional
    public Long createProduceRequest(ProduceRequestDto request) {
        final ProduceRequest createdProduceRequest = produceRequestMapper.produceRequestDtoToProduceRequest(request);
        final ProduceRequest savedProduceRequest = produceRequestRepository.save(createdProduceRequest);
        return savedProduceRequest.getId();
    }

    @Override
    @Transactional
    public List<ProduceRequestDto> getProduceRequestsByStatus(String status) {
        final Status requiredStatus = Status.valueOf(status.toUpperCase());
        final List<ProduceRequest> produceRequests = produceRequestRepository.findByStatus(requiredStatus);
        final List<ProduceRequestDto> produceRequestDtos = produceRequests
                .stream()
                .map(produceRequestMapper::produceRequestToProduceRequestDto)
                .collect(Collectors.toList());
        return produceRequestDtos;
    }

    @Override
    @Transactional
    public ProduceRequestDto getProduceRequest(Long id) throws BrewerySuchProduceRequestNotFoundException {
        final ProduceRequest produceRequest = produceRequestRepository.findById(id)
                .orElseThrow(()->new BrewerySuchProduceRequestNotFoundException("ProduceRequest with id = "+ id +" was not found"));
        final ProduceRequestDto produceRequestDto = produceRequestMapper.produceRequestToProduceRequestDto(produceRequest);
        return produceRequestDto;
    }

    @Override
    @Transactional
    public Long changeProduceRequestStatus(Long id, ProduceRequestDto produceRequestDto) throws BrewerySuchProduceRequestNotFoundException {
        final Status requiredStatus = Status.valueOf(produceRequestDto.getStatus().toUpperCase());
        final ProduceRequest produceRequestBeforeChangingStatus = produceRequestRepository.findById(id)
                .orElseThrow(()->new BrewerySuchProduceRequestNotFoundException("ProduceRequest with id = "+ id +" was not found"));
        produceRequestBeforeChangingStatus.setStatus(requiredStatus);
        final ProduceRequest produceRequestAfterChangingStatus = produceRequestRepository.save(produceRequestBeforeChangingStatus);
        return produceRequestAfterChangingStatus.getId();
    }

}

