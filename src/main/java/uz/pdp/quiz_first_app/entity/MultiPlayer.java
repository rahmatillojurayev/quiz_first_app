package uz.pdp.quiz_first_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "multi_player")
public class MultiPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    private User owner;

    @ManyToOne
    private User opponent;

    @ManyToOne
    private Question question;

    private Integer score;

    private Integer wrongAnswers;

    private Integer correctAnswers;

}