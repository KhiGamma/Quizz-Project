package fr.insa.quizz.repositories;

import fr.insa.quizz.models.Quizz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuizzRepository extends MongoRepository<Quizz, String> {

    Quizz findQuizzByIdEquals(String id);

    @Query("{answers:{id:$exits'?0'}}")
    List<Quizz> exitAnswerId(int id);

}
