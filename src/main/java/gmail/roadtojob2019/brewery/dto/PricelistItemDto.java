package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricelistItemDto {
    private Long beerId;
    private String beerName;
    private String beerDescription;
    private Double price;
    private String unit;
}
