package gmail.roadtojob2019.brewery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderDto {
    private Long id;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    private String name_beer;
    private Integer amount;
    private Long customer_id;
}
