package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.models.Answers;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizzRequest {

    private int number;
}
