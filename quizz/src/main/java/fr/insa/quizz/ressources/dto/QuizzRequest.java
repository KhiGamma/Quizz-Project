package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizzRequest {

    private String content;
    private String theme;
    private int difficulty;
    List<AnswerRequest> answers;

}
