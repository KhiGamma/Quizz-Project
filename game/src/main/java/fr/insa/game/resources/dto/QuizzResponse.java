package fr.insa.game.resources.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizzResponse {

    private String id;
    private String content;
    private String theme;
    private int difficulty;
    List<AnswerResponse> answers;

}
