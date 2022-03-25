package fr.insa.user.repositories;

import fr.insa.user.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsernameEquals(String username);

    User findUserByEmailEquals(String email);
}
