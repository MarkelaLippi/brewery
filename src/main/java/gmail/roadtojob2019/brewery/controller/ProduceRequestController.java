package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.service.ProduceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/brewery")
public class ProduceRequestController {

    private final ProduceRequestService produceRequestService;

    @PostMapping(value = "sales/requests", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduceRequest(@RequestBody ProduceRequestDto request) {
        return produceRequestService.createProduceRequest(request);
    }
}
