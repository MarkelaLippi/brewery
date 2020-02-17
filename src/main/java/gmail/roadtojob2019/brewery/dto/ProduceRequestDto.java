package gmail.roadtojob2019.brewery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProduceRequestDto {
    private Long id;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    private Long beerId;
    private Integer amount;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate term;
    private String status;
}
