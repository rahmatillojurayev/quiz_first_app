package uz.pdp.quiz_first_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean isCorrect;

    private String textUz;

    private String textRu;

    private String textEn;

    @ManyToOne
    private Question question;

}