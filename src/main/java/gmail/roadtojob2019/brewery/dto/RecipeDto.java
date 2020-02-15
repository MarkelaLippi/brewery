package gmail.roadtojob2019.brewery.dto;

import lombok.Data;

@Data
public class RecipeDto {
    private Long id;
    private Long beer_id;
    private String components;
}
