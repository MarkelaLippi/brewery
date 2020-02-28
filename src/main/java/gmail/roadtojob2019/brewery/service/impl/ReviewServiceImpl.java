package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchOrderNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchProductNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchReviewNotFoundException;
import gmail.roadtojob2019.brewery.mapper.ReviewMapper;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import gmail.roadtojob2019.brewery.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    private final ReviewMapper reviewMapper;

    @Override
    @Transactional
    public Long createReview(final ReviewDto reviewDto)
            throws BrewerySuchCustomerNotFoundException, BrewerySuchOrderNotFoundException {
        final Review newReview = reviewMapper.reviewDtoToReview(reviewDto);
        final Long customerId = reviewDto.getCustomerId();
        final Customer customer = customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new BrewerySuchCustomerNotFoundException("Customer with id = " + customerId + " was not found"));
        final Long orderId = reviewDto.getOrderId();
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BrewerySuchOrderNotFoundException("Order with id = " + orderId + " was not found"));
        newReview.setCustomer(customer);
        newReview.setOrder(order);
        final Review savedReview = reviewRepository.save(newReview);
        return savedReview.getId();
    }

    @Override
    @Transactional
    public Long changeReview(final Long id, final ReviewDto reviewDto) throws BrewerySuchReviewNotFoundException {
        final Review reviewBeforeChangingContent = reviewRepository.findById(id)
                .orElseThrow(() -> new BrewerySuchReviewNotFoundException("Review with id = " + id + " was not found"));
        ;
        final String newContent = reviewDto.getContent();
        reviewBeforeChangingContent.setContent(newContent);
        final Review reviewAfterChangingContent = reviewRepository.save(reviewBeforeChangingContent);
        return reviewAfterChangingContent.getId();
    }

    @Override
    @Transactional
    public void deleteReview(final Long id) throws BrewerySuchReviewNotFoundException {
        final boolean reviewIsExists = reviewRepository.existsById(id);
        if (!reviewIsExists) {
            throw new BrewerySuchReviewNotFoundException("No review with id = " + id + " was found.");
        }
        reviewRepository.deleteById(id);
    }
}
