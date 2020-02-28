package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchOrderNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchReviewNotFoundException;
import gmail.roadtojob2019.brewery.mapper.ReviewMapper;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import gmail.roadtojob2019.brewery.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    private final ReviewMapper reviewMapper;

    @Override
    @Transactional
    public Long createReview(ReviewDto reviewDto)
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
    public void deleteReview(final Long id) throws BrewerySuchReviewNotFoundException {
        final boolean isFound = reviewRepository.existsById(id);
        if (!isFound) {
            throw new BrewerySuchReviewNotFoundException("No review with id = " + id + " was found.");
        }
        reviewRepository.deleteById(id);
    }
}
