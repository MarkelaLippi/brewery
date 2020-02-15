package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.BeerDto;
import gmail.roadtojob2019.brewery.entity.Beer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    Beer sourceToDestination(BeerDto source);

    BeerDto destinationToSource(Beer destination);
}
