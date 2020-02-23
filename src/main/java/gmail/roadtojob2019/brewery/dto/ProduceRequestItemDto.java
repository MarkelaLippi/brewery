package gmail.roadtojob2019.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduceRequestItemDto {
    private Long id;
    private Long produceRequestId;
    private Long productId;
    private Double amount;
}
