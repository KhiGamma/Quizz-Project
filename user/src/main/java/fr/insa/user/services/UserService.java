package fr.insa.user.services;

import fr.insa.user.exceptions.ModelNotValidException;
import fr.insa.user.exceptions.UserNotFoundException;
import fr.insa.user.models.User;
import fr.insa.user.repositories.UserRepository;
import fr.insa.user.resources.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
public class UserService {

    private static final String USER_NOT_FOUND = "User with %s %s not found.";

    @Autowired
    UserRepository userRepository;

    public void userModelIsValid(UserRequest userRequest) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if (userRequest == null) {
            ex.getMessages().add("userRequest : null");
            throw ex;
        }

        if (userRequest.getUsername() == null || userRequest.getUsername().isBlank()) {
            ex.getMessages().add("Username is blank");
        }
        if (userRequest.getEmail() == null || userRequest.getEmail().isBlank()) {
            ex.getMessages().add("Email is blank");
        }
        if (userRequest.getPassword() == null || userRequest.getPassword().isBlank()) {
            ex.getMessages().add("Password is blank");
        }
        if (userRepository.findUserByUsernameEquals(userRequest.getUsername()) != null) {
            ex.getMessages().add("Username already used");
        }
        if (userRepository.existsUserByEmail(userRequest.getEmail())) {
            ex.getMessages().add("Email already used");
        }

        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }

    public User saveUser(UserRequest userRequest) {

        userModelIsValid(userRequest);

        User tosave = new User();

        tosave.setUsername(userRequest.getUsername());
        tosave.setEmail(userRequest.getEmail());
        tosave.setPassword(userRequest.getPassword());
        tosave.setCreatedAt(LocalDate.now());

        return this.userRepository.save(tosave);
    }

    public User getUserByUserName(String username) throws UserNotFoundException {
        User user = this.userRepository.findUserByUsernameEquals(username);

        if (user == null) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, "username", username));
        }
        else {
            return user;
        }
    }

    public User getUserById(String id) throws UserNotFoundException {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, "id", id)));
    }

    public User updateUser(String username, UserRequest userRequest) {
        User user = this.getUserByUserName(username);

        boolean needUpdate = false;

        if (StringUtils.hasLength(userRequest.getPassword())) {
            user.setPassword(userRequest.getPassword());
            needUpdate = true;
        }

        if (needUpdate) {
            return this.userRepository.save(user);
        }
        else {
            return user;
        }
    }

    public void deleteUser(String username) {
        String userId = this.getUserByUserName(username).getId();
        this.userRepository.deleteById(userId);
    }
}
