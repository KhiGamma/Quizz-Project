package fr.insa.quizz.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answers {

    @DBRef
    List<Quizz> quizz;

    private String answer;
    private boolean valid;
}
