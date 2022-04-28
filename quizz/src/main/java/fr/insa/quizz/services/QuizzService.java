package fr.insa.quizz.services;

import fr.insa.quizz.exceptions.ModelNotValidException;
import fr.insa.quizz.exceptions.QuizzNotFoundException;
import fr.insa.quizz.models.Answers;
import fr.insa.quizz.models.Quizz;
import fr.insa.quizz.repositories.QuizzRepository;
import fr.insa.quizz.ressources.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.*;

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


    private void quizzIsValid(QuizzRequest quizzRequest) throws ModelNotValidException {
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

        if (quizzRequest.getDifficulty() < 1 || quizzRequest.getDifficulty() > 3) {
            ex.getMessages().add("Difficulty not exist");
        }

        if (quizzRequest.getAnswers().size() != 4) {
            ex.getMessages().add("Answers count problem");
        }

        int somme = 0;

        for (AnswerRequest answerRequest : quizzRequest.getAnswers()) {
            somme += (answerIsValid(answerRequest, ex, quizzRequest.getAnswers().indexOf(answerRequest)+1))?1:0;
        }

        if (somme != 1){
            ex.getMessages().add("Nombre de réponses valides incorrect");
        }

        if(!ex.getMessages().isEmpty()) {
            throw  ex;
        }
    }

    public boolean answerIsValid(AnswerRequest answerRequest, ModelNotValidException ex, int nbAnswer) {

        if(answerRequest.getNbAnswer() != nbAnswer) {
            ex.getMessages().add("Answer number not valid");
        }

        if (answerRequest.getContent() == null || answerRequest.getContent().isBlank()) {
            ex.getMessages().add("Content is blank or null");
        }

        return answerRequest.isValid();
    }


    public void deleteQuizz(String quizzId) {
        this.quizzRepository.deleteById(quizzId);
    }


    public List<Quizz> getQuestions() {
        return this.quizzRepository.findAll();
    }

    public List<Quizz> getRandomQuestions() {

        Random random = new Random();
        int nbRand;
        int nbQuestions = Integer.parseInt(this.quizzRepository.nbQuestions().toString());
        List<Quizz> randomQuestions = new ArrayList<>();
        List<Integer> nbTire = new ArrayList<>();

        for(int i=0;i<5;i++) {

            do {
                nbRand = random.nextInt(nbQuestions) + 1;
            } while(nbTire.contains(nbRand));
            nbTire.add(nbRand);
            Quizz quizz = this.quizzRepository.randomQuestions(nbRand);
            randomQuestions.add(quizz);
        }

        return randomQuestions;
    }

    public int checkAnswers(List<UserAnswersRequest> userAnswersRequests) {
        int score = 0;

        for(UserAnswersRequest userAnswer : userAnswersRequests) {
            Quizz q = this.getQuizzById(userAnswer.getId());
            score += q.getAnswers().get(userAnswer.getNbAnswer() - 1).isValid()?1:0;
        }
        return score;
    }
}
