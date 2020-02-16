package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDto {
    private Long id;
    private String name;
    private Double amount;
    private String unit;
}
