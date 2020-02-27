package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;

public interface ReviewService {
    Long createReview(ReviewDto reviewDto) throws BrewerySuchCustomerNotFoundException;
}
