package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import fr.insa.quizz.models.Quizz;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizzResponse {

    private String content;
    private String theme;
    private int difficulty;
    List<AnswerResponse> answers;

    public QuizzResponse(Quizz quizz) {
        this.content = quizz.getContent();
        this.theme = quizz.getTheme();
        this.difficulty = quizz.getDifficulty();
        this.answers = new ArrayList<>();

        for(Answers a : quizz.getAnswers()) {
            this.answers.add(new AnswerResponse(a));
        }

    }

}
