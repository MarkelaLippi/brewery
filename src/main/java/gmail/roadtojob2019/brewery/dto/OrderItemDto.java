package gmail.roadtojob2019.brewery.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private Double amount;
}
