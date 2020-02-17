package gmail.roadtojob2019.brewery.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "produce_requests")
public class ProduceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "beer_id")
    private Long beerId;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "expiration_date")
    private LocalDate term;
    @Column(name = "status")
    private String status;
}
