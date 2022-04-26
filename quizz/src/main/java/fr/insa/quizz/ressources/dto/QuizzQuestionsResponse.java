package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import fr.insa.quizz.models.Quizz;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizzQuestionsResponse {

    private String content;
    private String theme;
    private int difficulty;
    List<AnswerQuestionsResponse> answers;

    public QuizzQuestionsResponse(Quizz quizz) {
        this.content = quizz.getContent();
        this.theme = quizz.getTheme();
        this.difficulty = quizz.getDifficulty();
        this.answers = new ArrayList<AnswerQuestionsResponse>();

        for(Answers a : quizz.getAnswers()) {
            this.answers.add(new AnswerQuestionsResponse(a));
        }

    }

}
