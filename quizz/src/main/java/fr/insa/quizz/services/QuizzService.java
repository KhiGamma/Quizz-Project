package fr.insa.quizz.services;

import fr.insa.quizz.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizzService {

    @Autowired
    QuizzRepository quizzRepository;
}
