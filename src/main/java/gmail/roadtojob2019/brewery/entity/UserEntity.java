package gmail.roadtojob2019.brewery.entity;

import gmail.roadtojob2019.brewery.converters.RoleConverter;
import gmail.roadtojob2019.brewery.security.UserRole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_entities")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    @Convert(converter = RoleConverter.class)
    private UserRole userRole;
}
