package fr.insa.user.repositories;

import fr.insa.user.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByUsernameEquals(String username);

    boolean existsUserByEmail(String eamil);

    Optional<User> findUserByEmailAndPassword(String email, String password);

    List<User> findAllByOrderByScoreDesc();
}
