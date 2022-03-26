package fr.insa.quizz.ressources.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerRequest {

    private String content;
    private boolean valid;
}
