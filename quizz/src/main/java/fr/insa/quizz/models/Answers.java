package fr.insa.quizz.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("quizz")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answers {

    @Id
    private String id;

    private Quizz quizz;

    private String answer;
    private boolean valid;
}
