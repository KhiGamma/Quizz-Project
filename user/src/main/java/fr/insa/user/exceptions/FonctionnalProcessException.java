package fr.insa.user.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FonctionnalProcessException extends Exception {
    public FonctionnalProcessException(String message) {
        super(message);
    }
}
