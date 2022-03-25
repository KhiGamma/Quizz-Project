package fr.insa.user.repositories;

import fr.insa.user.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsernameEquals(String username);

    boolean existsUserByEmail(String eamil);

    Optional<User> findUserByEmailAAndPassword(String email, String password);

}
