package fr.insa.user.resources;

import fr.insa.user.resources.dto.UserRequest;
import fr.insa.user.resources.dto.UserResponse;
import fr.insa.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        return new UserResponse(this.userService.saveUser(userRequest));
    }

    @DeleteMapping("{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        this.userService.deleteUser(username);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("{username}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String username, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(new UserResponse(this.userService.updateUser(username, userRequest)));
    }
}
