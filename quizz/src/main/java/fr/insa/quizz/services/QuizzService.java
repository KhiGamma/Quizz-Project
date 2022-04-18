package fr.insa.quizz.services;

import fr.insa.quizz.exceptions.ModelNotValidException;
import fr.insa.quizz.exceptions.QuizzNotFoundException;
import fr.insa.quizz.models.Answers;
import fr.insa.quizz.models.Quizz;
import fr.insa.quizz.repositories.QuizzRepository;
import fr.insa.quizz.ressources.dto.AnswerRequest;
import fr.insa.quizz.ressources.dto.AnswerResponse;
import fr.insa.quizz.ressources.dto.QuizzRequest;
import fr.insa.quizz.ressources.dto.QuizzResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuizzService {

    @Autowired
    QuizzRepository quizzRepository;

    public Quizz getQuizzById(String id) throws QuizzNotFoundException {

        return this.quizzRepository.findById(id).orElseThrow(()-> new QuizzNotFoundException("No quizz find"));
    }

    public Quizz saveQuizz(QuizzRequest quizzRequest) {

        quizzIsValid(quizzRequest);

        Quizz tosave = new Quizz();

        tosave.setContent(quizzRequest.getContent());
        tosave.setTheme(quizzRequest.getTheme());
        tosave.setDifficulty(quizzRequest.getDifficulty());
        tosave.setAnswers(new ArrayList<>());

        for(AnswerRequest a : quizzRequest.getAnswers()) {
            Answers answer = new Answers(a);
            tosave.getAnswers().add(answer);
        }

        return this.quizzRepository.save(tosave);
    }


    private void quizzIsValid(QuizzRequest quizzRequest) throws ModelNotValidException{
        ModelNotValidException ex = new ModelNotValidException();

        if (quizzRequest == null) {
            ex.getMessages().add("quizzRequest : null");
            throw ex;
        }

        if (quizzRequest.getContent() == null || quizzRequest.getContent().isBlank()) {
            ex.getMessages().add("Content is blank or null");
        }

        if (quizzRequest.getTheme() == null || quizzRequest.getTheme().isBlank()) {
            ex.getMessages().add("Theme is blank or null");
        }

        if (quizzRequest.getDifficulty() != 1 || quizzRequest.getDifficulty() != 2 || quizzRequest.getDifficulty() != 3) {
            ex.getMessages().add("Difficulty not exist");
        }

        if (quizzRequest.getAnswers().size() != 4) {
            ex.getMessages().add("Answers number problem");
        }

    }


    public void deleteQuizz(String quizzId) {

        this.quizzRepository.deleteById(quizzId);
    }
}
