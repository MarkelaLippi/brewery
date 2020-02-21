package gmail.roadtojob2019.brewery.dto;

import lombok.Data;

@Data
public class CustomerSignUpRequestDto {
    private String email;
    private String password;
    private String phone;
    private String fullName;
}
