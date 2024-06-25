package uz.pdp.quiz_first_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class QuizFirstAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizFirstAppApplication.class, args);
    }

}
