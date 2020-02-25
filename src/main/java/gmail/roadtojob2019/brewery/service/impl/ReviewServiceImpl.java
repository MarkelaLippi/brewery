package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
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
    public Long createReview(ReviewDto reviewDto) {
        Review newReview = reviewMapper.reviewDtoToReview(reviewDto);
        final Customer customer = customerRepository.findById(reviewDto.getCustomerId()).get();
        final Order order = orderRepository.findById(reviewDto.getOrderId()).get();
        newReview.setCustomer(customer);
        newReview.setOrder(order);
        Review savedReview = reviewRepository.save(newReview);
        return savedReview.getId();
    }
}
