package gmail.roadtojob2019.brewery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "amount")
    private Double amount;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;



}
