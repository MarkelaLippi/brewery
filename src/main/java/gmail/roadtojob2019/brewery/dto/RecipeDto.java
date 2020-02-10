package gmail.roadtojob2019.brewery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class RecipeDto {
    private Long id;
    private Long beer_id;
    private Map<String,Double> components=new HashMap<>();
}
