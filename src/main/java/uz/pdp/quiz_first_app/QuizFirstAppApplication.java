package uz.pdp.quiz_first_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@ComponentScan(value = "uz.pdp")
@SpringBootApplication
public class QuizFirstAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizFirstAppApplication.class, args);
    }

}
