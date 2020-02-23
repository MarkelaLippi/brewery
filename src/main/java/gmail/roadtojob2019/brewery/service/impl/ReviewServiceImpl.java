package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import gmail.roadtojob2019.brewery.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public Long createReview(ReviewDto reviewDto) {
        final LocalDate date = reviewDto.getDate();
        final String content = reviewDto.getContent();
        final Customer customer = customerRepository.getOne(reviewDto.getCustomerId());
        final Order order = orderRepository.findById(reviewDto.getOrderId()).get();
        final Review newReview = Review.builder()
                .date(date)
                .content(content)
                .customer(customer)
                .order(order)
                .build();
        Review savedReview = reviewRepository.save(newReview);
        return savedReview.getId();
    }
}
