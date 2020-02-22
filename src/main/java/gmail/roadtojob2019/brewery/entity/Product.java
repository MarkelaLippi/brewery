package gmail.roadtojob2019.brewery.entity;

import gmail.roadtojob2019.brewery.converters.TypeConverter;
import gmail.roadtojob2019.brewery.converters.UnitConverter;
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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "type")
    @Convert(converter = TypeConverter.class)
    private Type type;
    @Convert(converter = UnitConverter.class)
    @Column(name = "unit")
    private Unit unit;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    private Storage storage;
}
