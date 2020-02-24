package gmail.roadtojob2019.brewery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long id;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    private Long customerId;
    List<OrderItemDto> orderItemDtos=new ArrayList<>();
}
