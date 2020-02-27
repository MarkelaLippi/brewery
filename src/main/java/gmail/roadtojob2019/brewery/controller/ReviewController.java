package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchOrderNotFoundException;
import gmail.roadtojob2019.brewery.service.ReviewService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api
@RequestMapping("/brewery")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "customer/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createReview(@RequestBody ReviewDto reviewDto) throws BrewerySuchCustomerNotFoundException, BrewerySuchOrderNotFoundException {
        return reviewService.createReview(reviewDto);
    }
}
