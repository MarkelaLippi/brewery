package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;
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

    @PostConstruct
    public void init() {
        produceRequestRepository.save(ProduceRequest.builder()
                .date(LocalDate.of(2020, 2, 5))
                .beerId(1L)
                .amount(200)
                .term(LocalDate.of(2020, 2, 10))
                .status("New")
                .build());
    }

    @Override
    public Long createProduceRequest(ProduceRequestDto request) {
        return produceRequestRepository
                .save(produceRequestMapper.produceRequestDtoToProduceRequest(request))
                .getId();
    }

    @Override
    public List<ProduceRequestDto> getProduceRequestsByStatus(String status) {
        return produceRequestRepository
                .findByStatusIgnoreCase(status)
                .stream()
                .map(produceRequestMapper::produceRequestToProduceRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProduceRequestDto getProduceRequest(Long id) {
        return produceRequestMapper.produceRequestToProduceRequestDto(produceRequestRepository.findById(id).get());
    }

    @Override
    public Long changeProduceRequestStatus(Long id, ProduceRequestDto produceRequestDto) {
        ProduceRequest produceRequest = produceRequestRepository.findById(id).get();
        produceRequest.setStatus(produceRequestDto.getStatus());
        return produceRequest.getId();
    }

}

