package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComponentDto {
    private Long id;
    private Long recipe_id;
    private Double amount;
}
