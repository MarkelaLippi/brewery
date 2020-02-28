package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchOrderNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchProductNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchReviewNotFoundException;

public interface ReviewService {
    Long createReview(ReviewDto reviewDto)
            throws BrewerySuchCustomerNotFoundException, BrewerySuchOrderNotFoundException;

    void deleteReview(Long id) throws BrewerySuchReviewNotFoundException;

    Long changeReview(Long id, ReviewDto reviewDto) throws BrewerySuchReviewNotFoundException;
}
