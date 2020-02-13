package gmail.roadtojob2019.brewery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomerSignUpRequestDto {
    private String email;
    private String phone;
    private String login;
    private String password;
}
