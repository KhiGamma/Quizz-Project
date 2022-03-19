package fr.insa.user.resources;

import fr.insa.user.resources.dto.UserRequest;
import fr.insa.user.resources.dto.UserResponse;
import fr.insa.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserResource extends CommonResource {

    @Autowired
    UserService userService;

    @GetMapping
    public UserResponse getUser(@RequestParam(name = "name") String name) {
        return new UserResponse(this.userService.getUserByUserName(name));
    }

    @PostMapping
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        return new UserResponse(this.userService.saveUser(userRequest));
    }
}
