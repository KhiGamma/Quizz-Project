package fr.insa.quizz.repositories;

import fr.insa.quizz.models.Quizz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuizzRepository extends MongoRepository<Quizz, String> {

}
