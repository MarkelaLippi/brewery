package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchProduceRequestNotFoundException;
import gmail.roadtojob2019.brewery.service.ProduceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brewery")
public class ProduceRequestController {

    private final ProduceRequestService produceRequestService;

    @PostMapping(value = "sales/requests", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduceRequest(@RequestBody final ProduceRequestDto request) {
        return produceRequestService.createProduceRequest(request);
    }

    @GetMapping(value = "brewer/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<ProduceRequestDto> getProduceRequestsByStatus(@RequestParam(value="status") final String status) {
        return produceRequestService.getProduceRequestsByStatus(status);
    }

    @GetMapping(value = "brewer/requests/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProduceRequestDto getProduceRequest(@PathVariable final Long id) throws BrewerySuchProduceRequestNotFoundException {
        return produceRequestService.getProduceRequest(id);
    }

    @PatchMapping(value = "brewer/requests/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Long changeProduceRequestStatus(@PathVariable final Long id, @RequestBody final ProduceRequestDto request) throws BrewerySuchProduceRequestNotFoundException {
        return produceRequestService.changeProduceRequestStatus(id, request);
    }
}
