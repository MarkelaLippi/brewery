package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.ProduceRequestDto;
import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduceRequestMapper {
    ProduceRequest sourceToDestination(ProduceRequestDto source);

    ProduceRequestDto destinationToSource(ProduceRequest destination);
}
