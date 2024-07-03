package uz.pdp.quiz_first_app.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordResp {

    private String message;

    private String resetPasswordToken;

}
