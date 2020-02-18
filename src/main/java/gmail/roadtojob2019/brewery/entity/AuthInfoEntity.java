package gmail.roadtojob2019.brewery.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AuthInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

