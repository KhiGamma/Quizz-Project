package fr.insa.user.resources.dto;

import fr.insa.user.models.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;
    private int nbGame;
    private int score;

    public UserResponse(User userSaved) {
        this.setUsername(userSaved.getUsername());
        this.setEmail(userSaved.getEmail());
        this.setPassword(userSaved.getPassword());
        this.setCreatedAt(userSaved.getCreatedAt());
        this.setNbGame(userSaved.getNbGame());
        this.setScore(userSaved.getScore());
    }
}
