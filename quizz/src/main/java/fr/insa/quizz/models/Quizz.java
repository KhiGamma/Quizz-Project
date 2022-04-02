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
public class Quizz {

    @Id
    private String id;

    private String content;
    private String theme;
    private int difficulty;

    //@DBRef
    List<Answers> answers;
}
