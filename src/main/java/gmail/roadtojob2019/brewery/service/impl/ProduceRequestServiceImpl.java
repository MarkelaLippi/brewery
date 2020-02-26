package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import gmail.roadtojob2019.brewery.entity.Status;
import gmail.roadtojob2019.brewery.mapper.ProduceRequestItemMapper;
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
        Status requiredStatus = Status.valueOf(status.toUpperCase());
        List<ProduceRequest> produceRequests = produceRequestRepository.findByStatus(requiredStatus);
        List<ProduceRequestDto> produceRequestDtos = produceRequests
                .stream()
                .map(produceRequestMapper::produceRequestToProduceRequestDto)
                .collect(Collectors.toList());
        return produceRequestDtos;
    }

    @Override
    @Transactional
    public ProduceRequestDto getProduceRequest(Long id) {
        ProduceRequest produceRequest = produceRequestRepository.findById(id).get();
        ProduceRequestDto produceRequestDto = produceRequestMapper.produceRequestToProduceRequestDto(produceRequest);
        return produceRequestDto;
    }

    @Override
    @Transactional
    public Long changeProduceRequestStatus(Long id, ProduceRequestDto produceRequestDto) {
        Status requiredStatus = Status.valueOf(produceRequestDto.getStatus().toUpperCase());
        ProduceRequest produceRequestBeforeChangingStatus = produceRequestRepository.findById(id).get();
        produceRequestBeforeChangingStatus.setStatus(requiredStatus);
        ProduceRequest produceRequestAfterChangingStatus = produceRequestRepository.save(produceRequestBeforeChangingStatus);
        return produceRequestAfterChangingStatus.getId();
    }

}

