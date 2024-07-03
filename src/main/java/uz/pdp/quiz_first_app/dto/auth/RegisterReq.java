package uz.pdp.quiz_first_app.dto.auth;

import lombok.Data;

@Data
public class RegisterReq {

    private String email;
    private String password;

}
