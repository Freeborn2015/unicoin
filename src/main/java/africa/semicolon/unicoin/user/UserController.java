package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.user.userRequest.LoginRequest;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping(path = "api/v1/user")
    public class UserController {

        @Autowired
        UserService userService;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws MessagingException {
            return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
        }

    }
