package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RecipeDto {
    private Long id;
    private Long productId;
    private List<RecipeItemDto> recipeItemDtos = new ArrayList<>();
}
