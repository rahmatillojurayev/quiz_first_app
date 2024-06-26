package uz.pdp.quiz_first_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "text_uz")
    private String textUz;

    @Column(name = "text_ru")
    private String textRu;

    @Column(name = "text_en")
    private String textEn;


    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}