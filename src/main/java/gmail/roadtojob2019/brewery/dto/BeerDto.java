package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeerDto {
    private Long id;
    private String name;
    private String type;
    private String alcohol;
    private Integer amount;
    private String recipe;
}
