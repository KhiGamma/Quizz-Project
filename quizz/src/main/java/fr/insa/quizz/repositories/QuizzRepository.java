package fr.insa.quizz.repositories;

import fr.insa.quizz.models.Quizz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizzRepository extends MongoRepository<Quizz, String> {

    Quizz findQuizzByNumber(int number);
}
