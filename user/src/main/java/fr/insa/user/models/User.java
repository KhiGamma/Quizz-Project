package fr.insa.user.models;

import fr.insa.user.repositories.UserRepository;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;
    private int nbGame;
    private int score;

}
