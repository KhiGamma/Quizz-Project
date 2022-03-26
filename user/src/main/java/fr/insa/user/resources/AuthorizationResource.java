package fr.insa.user.resources;

import fr.insa.user.models.User;
import fr.insa.user.resources.dto.UserLoginRequest;
import fr.insa.user.resources.dto.UserRequest;
import fr.insa.user.resources.dto.UserResponse;
import fr.insa.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("authorization")
public class AuthorizationResource {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserLoginRequest userLoginRequest) {
        return this.userService.getUserByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword())
                .map(User -> ResponseEntity.ok(jwtTokenProvider.generateToken(User.getId())))
                .orElse(new ResponseEntity("Invalide email or passe", HttpStatus.UNAUTHORIZED));
    }


    @GetMapping("is-authorized")
    public ResponseEntity isAuthorize(@RequestHeader("Authorization") String jwt) {
        if(StringUtils.hasText(jwt) && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);

            if (jwtTokenProvider.validateToken(jwt)) {
                String userId = jwtTokenProvider.getUserIdFromJWT(jwt);

                return ResponseEntity.ok(new UserResponse(this.userService.getUserById(userId)));
            }
        }
        return new ResponseEntity("You are not allowed to access this ressource", HttpStatus.UNAUTHORIZED);
    }
}
