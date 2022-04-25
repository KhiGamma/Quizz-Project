package fr.insa.user.resources;

import fr.insa.user.resources.dto.UserLeaderboardResponse;
import fr.insa.user.resources.dto.UserRequest;
import fr.insa.user.resources.dto.UserResponse;
import fr.insa.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/leaderboard")
    public ResponseEntity<List<UserLeaderboardResponse>> leaderboard() {
        return ResponseEntity.ok(
                new ArrayList<>(
                        this.userService.getLeaderBoard().stream().map(UserLeaderboardResponse::new).collect(Collectors.toList())
                ));
    }
}
