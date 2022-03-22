package fr.insa.quizz.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("quizz")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quizz {

    @Id
    private String id;

    private int number;
    private String content;
    private String theme;
    private int difficulty;
    private Answers answers;
}
