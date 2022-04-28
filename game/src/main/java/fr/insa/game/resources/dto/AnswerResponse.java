package fr.insa.game.resources.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {

    private String content;
    private int nbAnswer;

}
