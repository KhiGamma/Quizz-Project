package fr.insa.game.resources.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAnswersRequest {

    private String id;
    private int nbAnswer;
}
