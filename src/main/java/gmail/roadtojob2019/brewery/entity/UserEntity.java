package gmail.roadtojob2019.brewery.entity;

import gmail.roadtojob2019.brewery.converters.RoleConverter;
import gmail.roadtojob2019.brewery.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
