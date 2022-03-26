package fr.insa.quizz.services;

import fr.insa.quizz.exceptions.ModelNotValidException;
import fr.insa.quizz.models.Answers;
import fr.insa.quizz.models.Quizz;
import fr.insa.quizz.repositories.QuizzRepository;
import fr.insa.quizz.ressources.dto.AnswerRequest;
import fr.insa.quizz.ressources.dto.AnswerResponse;
import fr.insa.quizz.ressources.dto.QuizzRequest;
import fr.insa.quizz.ressources.dto.QuizzResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuizzService {

    @Autowired
    QuizzRepository quizzRepository;

    public Quizz getQuizzById(String id) {

        return this.quizzRepository.findQuizzByIdEquals(id);
    }

    public Quizz saveQuizz(QuizzRequest quizzRequest) {

        //TODO effectuer les verif lors de l'insertion des donnees
        // quizzIsValid(quizzRequest);

        Quizz tosave = new Quizz();

        tosave.setContent(quizzRequest.getContent());
        tosave.setTheme(quizzRequest.getTheme());
        tosave.setDifficulty(quizzRequest.getDifficulty());
        tosave.setAnswers(new ArrayList<>());

        for(AnswerRequest a : quizzRequest.getAnswers()) {
            Answers answer = new Answers(a);
            answer.setId(genererIdAsnwer());
            tosave.getAnswers().add(answer);
        }

        return this.quizzRepository.save(tosave);
    }

    private int genererIdAsnwer() {

        int id = 0;

        do {
            id = (int) (Math.random() * (10 - 1 + 1) + 1);
        } while(this.quizzRepository.exitAnswerId(id).isPresent());

        return id;
    }

    private void quizzIsValid(QuizzRequest quizzRequest) {

    }

    public boolean test(int id) {
        return this.quizzRepository.exitAnswerId(id).isPresent();
    }
}
