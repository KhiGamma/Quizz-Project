package fr.insa.user.services;

import fr.insa.user.exceptions.ModelNotValidException;
import fr.insa.user.models.User;
import fr.insa.user.repositories.UserRepository;
import fr.insa.user.resources.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean UserModelIsValid(UserRequest userRequest) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        // TODO : verif mail / usernam and passwords

        return true;
    }

    public User saveUser(UserRequest userRequest) {

        // TODO : verif mail / usernam and passwords

        User tosave = new User();

        tosave.setUsername(userRequest.getUsername());
        tosave.setEmail(userRequest.getEmail());
        tosave.setPassword(userRequest.getPassword());
        tosave.setCreatedAt(LocalDate.now());

        return this.userRepository.save(tosave);
    }

    public User getUserByUserName(String name) {
        return this.userRepository.findUserByUsernameEquals(name);
    }
}
