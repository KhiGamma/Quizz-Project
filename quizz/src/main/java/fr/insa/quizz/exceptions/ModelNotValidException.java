package fr.insa.quizz.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ModelNotValidException  extends RuntimeException{
    private List<String> messages;

    public ModelNotValidException() {
        this.messages = new ArrayList<>(); }
}
