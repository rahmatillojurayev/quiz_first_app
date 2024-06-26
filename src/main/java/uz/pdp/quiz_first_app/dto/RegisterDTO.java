package uz.pdp.quiz_first_app.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private Integer activationCode;

}
