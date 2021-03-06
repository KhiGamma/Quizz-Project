package fr.insa.quizz.models;

import fr.insa.quizz.ressources.dto.AnswerRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document("quizz")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answers {

    private String content;
    private int nbAnswer;
    private boolean valid;



    public Answers(AnswerRequest answerRequest) {

        this.content = answerRequest.getContent();
        this.nbAnswer = answerRequest.getNbAnswer();
        this.valid = answerRequest.isValid();
    }
}