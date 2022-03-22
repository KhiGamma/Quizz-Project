package fr.insa.quizz.services;

import fr.insa.quizz.exceptions.ModelNotValidException;
import fr.insa.quizz.repositories.QuizzRepository;
import fr.insa.quizz.ressources.dto.QuizzRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizzService {

    @Autowired
    QuizzRepository quizzRepository;

    public Object getQuizzByNumber(int num) {
        return this.quizzRepository.findQuizzByNumber(num);
    }
}
