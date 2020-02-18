package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class PricelistDto {
    private Long id;
    private Set<PricelistUnitDto>beers=new HashSet<>();
}
