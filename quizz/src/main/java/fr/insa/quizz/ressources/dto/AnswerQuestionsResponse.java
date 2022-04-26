package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerQuestionsResponse {

    private String content;
    private boolean valid;
    private int nbAnswer;

    public AnswerQuestionsResponse(Answers answers) {

        this.content = answers.getContent();
        this.valid = answers.isValid();
        this.nbAnswer = answers.getNbAnswer();
    }

}
