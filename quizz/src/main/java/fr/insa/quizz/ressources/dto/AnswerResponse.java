package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {

    private int id;
    private String content;

    public AnswerResponse(Answers answers) {

        this.id = answers.getId();
        this.content = answers.getContent();
    }

}
