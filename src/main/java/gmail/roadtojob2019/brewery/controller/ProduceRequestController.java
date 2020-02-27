package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchProduceRequestNotFoundException;
import gmail.roadtojob2019.brewery.service.ProduceRequestService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Api
@RequestMapping("/brewery")
public class ProduceRequestController {

    private final ProduceRequestService produceRequestService;

    @PostMapping(value = "sales/requests", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduceRequest(@RequestBody ProduceRequestDto request) {
        return produceRequestService.createProduceRequest(request);
    }

    @GetMapping(value = "brewer/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<ProduceRequestDto> getProduceRequestsByStatus(@RequestParam(value="status") String status) {
        return produceRequestService.getProduceRequestsByStatus(status);
    }

    @GetMapping(value = "brewer/requests/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProduceRequestDto getProduceRequest(@PathVariable Long id) throws BrewerySuchProduceRequestNotFoundException, BrewerySuchCustomerNotFoundException {
        return produceRequestService.getProduceRequest(id);
    }

    @PatchMapping(value = "brewer/requests/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Long changeProduceRequestStatus(@PathVariable Long id, @RequestBody ProduceRequestDto request) {
        return produceRequestService.changeProduceRequestStatus(id, request);
    }

}
