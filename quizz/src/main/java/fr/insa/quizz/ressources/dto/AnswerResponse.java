package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {

    private String content;
    private int nbAnswer;

    public AnswerResponse(Answers answers) {

        this.content = answers.getContent();
        this.nbAnswer = answers.getNbAnswer();
    }

}
