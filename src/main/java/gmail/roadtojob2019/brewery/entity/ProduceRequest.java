package gmail.roadtojob2019.brewery.entity;


import gmail.roadtojob2019.brewery.converters.StatusConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "produce_requests")
public class ProduceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "expiration_date")
    private LocalDate term;
    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @OneToMany
    @JoinColumn(name = "produce_request_id", referencedColumnName = "id")
    Set<ProduceRequestItem> produceRequestItems=new HashSet<>();


    /*
        @Column(name = "beer_id")
        private Long beerId;
        @Column(name = "amount")
        private Integer amount;
    */
}
