package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/brewery")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "customer/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }
}