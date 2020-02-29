package gmail.roadtojob2019.brewery.dto;

import lombok.Data;

@Data
public class PricelistItemDto {
    private Long beerId;
    private String beerName;
    private String beerDescription;
    private Double price;
    private String unit;
}
