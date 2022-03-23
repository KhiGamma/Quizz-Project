package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import fr.insa.quizz.models.Quizz;

import java.util.List;

public class QuizzResponse {

    private String content;
    private String theme;
    List<AnswerResponse> answers;

    public QuizzResponse(Quizz quizz) {
        this.content = quizz.getContent();
        this.theme = quizz.getTheme();

        for(Answers a : quizz.getAnswers()) {
            this.answers.add(new AnswerResponse(a));
        }

    }

}
