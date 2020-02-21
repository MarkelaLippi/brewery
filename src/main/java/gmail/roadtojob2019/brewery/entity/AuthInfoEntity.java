package gmail.roadtojob2019.brewery.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auth_info_entities")
public class AuthInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

