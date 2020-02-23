package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeItemDto {
    private Long id;
    private Long recipeId;
    private Long productId;
    private Double amount;
}
