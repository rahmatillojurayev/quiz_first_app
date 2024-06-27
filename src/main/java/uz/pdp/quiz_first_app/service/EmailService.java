package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.RegisterDTO;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    private void sendSimpleMessage(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Confirm code");
        message.setText(text);
        emailSender.send(message);
    }

    public void sendEmailConfirmCode(RegisterDTO registerDTO) {
        Random random = new Random();
        registerDTO.setActivationCode(random.nextInt(1_000, 10_000));
        sendSimpleMessage(registerDTO.getEmail(), "Confirmation code: " + registerDTO.getActivationCode());
    }

}