package gmail.roadtojob2019.brewery.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String email;
    private String phone;
    private String login;
    private String password;
}
