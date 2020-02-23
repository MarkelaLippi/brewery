package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mappings({
            @Mapping(target = "customer.id", source = "customerId"),
            @Mapping(target = "order.id", source = "orderId")
    })
    Review reviewDtoToReview(ReviewDto reviewDto);

    @Mappings({
            @Mapping(target = "customerId", source = "customer.id"),
            @Mapping(target = "orderId", source = "order.id")
    })
    ReviewDto reviewToReviewDto(Review review);
}
