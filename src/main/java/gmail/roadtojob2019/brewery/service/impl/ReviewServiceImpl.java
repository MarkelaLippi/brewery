package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import gmail.roadtojob2019.brewery.entity.Customer;
import gmail.roadtojob2019.brewery.entity.Order;
import gmail.roadtojob2019.brewery.entity.Review;
import gmail.roadtojob2019.brewery.repository.CustomerRepository;
import gmail.roadtojob2019.brewery.repository.OrderRepository;
import gmail.roadtojob2019.brewery.repository.ReviewRepository;
import gmail.roadtojob2019.brewery.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        orderRepository.save(Order.builder().build());

/*
        customerRepository.save(Customer.builder()
                .fullName("Ivanov Ivan")
                .email("Ivanov@gmail.com")
                .phone("+375297772255")
                .build());
*/
    }

    @Override
    public Long createReview(ReviewDto reviewDto) {
        final Review newReview = Review.builder()
                .date(reviewDto.getDate())
                .content(reviewDto.getContent())
                .customer(customerRepository.getOne(reviewDto.getCustomerId()))
                .order(orderRepository.getOne(reviewDto.getOrderId()))
                .build();
        return reviewRepository
                .save(newReview)
                .getId();
    }
}
