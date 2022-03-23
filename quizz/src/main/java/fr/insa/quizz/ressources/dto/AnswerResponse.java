package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;

public class AnswerResponse {

    private  String id;
    private String content;

    public AnswerResponse(Answers answers) {

        this.id = answers.getId();
        this.content = answers.getAnswer();
    }

}
