package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComponentDto {
    private Long id;
    private Long ingredientId;
    private String ingredientName;
    private Double amount;
    private String unit;
}
