package gmail.roadtojob2019.brewery.mapper;

import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerSignUpRequestMapper {

    UserEntity sourceToDestination(CustomerSignUpRequestDto dto);

}
