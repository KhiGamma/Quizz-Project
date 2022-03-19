package fr.insa.user.services;

import fr.insa.user.models.User;
import fr.insa.user.repositories.UserRepository;
import fr.insa.user.resources.dto.UserRequest;
import fr.insa.user.resources.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(UserRequest userRequest) {
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
