package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String unit;
    private Double amount;
}
