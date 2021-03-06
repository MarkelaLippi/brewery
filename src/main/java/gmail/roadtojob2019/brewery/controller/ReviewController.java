package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchOrderNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchReviewNotFoundException;
import gmail.roadtojob2019.brewery.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "Access to reviews")
@RequestMapping("/brewery")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "customer/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create review")
    public Long createReview(@RequestBody final ReviewDto reviewDto) throws BrewerySuchCustomerNotFoundException, BrewerySuchOrderNotFoundException {
        return reviewService.createReview(reviewDto);
    }

    @PatchMapping(value = "customer/reviews/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Change review")
    public Long changeReview(@PathVariable final Long id, @RequestBody final ReviewDto reviewDto) throws BrewerySuchReviewNotFoundException {
        return reviewService.changeReview(id, reviewDto);
    }

    @DeleteMapping(value = "customer/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete review")
    public void deleteReview(@PathVariable final Long id) throws BrewerySuchReviewNotFoundException {
        reviewService.deleteReview(id);
    }
}
