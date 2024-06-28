package uz.pdp.quiz_first_app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String textUz;

    private String textRu;

    private String textEn;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "question")
    private List<Option> options;

}