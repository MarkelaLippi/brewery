package gmail.roadtojob2019.brewery.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RecipeDto {
    private Long id;
    private Set<ComponentDto> components = new HashSet<>();
    private BeerDto beer;
}
