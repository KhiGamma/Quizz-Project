package fr.insa.gateway.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponse {
    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;
    private int nbGame;
    private int score;
}
