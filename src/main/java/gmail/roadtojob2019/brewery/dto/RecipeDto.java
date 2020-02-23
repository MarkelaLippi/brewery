package gmail.roadtojob2019.brewery.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeDto {
    private Long id;
    private Long productId;
    private List<RecipeItemDto> recipeItemDtos = new ArrayList<>();
}
