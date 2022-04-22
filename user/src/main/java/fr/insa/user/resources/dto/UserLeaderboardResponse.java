package fr.insa.user.resources.dto;

import fr.insa.user.models.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLeaderboardResponse {

    private String username;
    private int nbGame;
    private int score;

    public UserLeaderboardResponse(User userSaved) {
        this.setUsername(userSaved.getUsername());
        this.setNbGame(userSaved.getNbGame());
        this.setScore(userSaved.getScore());
    }
}
