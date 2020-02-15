package gmail.roadtojob2019.brewery.repository;

import gmail.roadtojob2019.brewery.dto.ReviewDto;
import gmail.roadtojob2019.brewery.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review sourceToDestination(ReviewDto source);

    ReviewDto destinationToSource(Review destination);
}
