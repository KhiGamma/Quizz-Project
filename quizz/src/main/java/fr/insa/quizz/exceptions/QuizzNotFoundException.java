package fr.insa.quizz.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuizzNotFoundException extends RuntimeException {
    public QuizzNotFoundException(String message) {
        super(message);
    }
}