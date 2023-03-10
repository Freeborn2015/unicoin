package africa.semicolon.unicoin.registration;

import africa.semicolon.unicoin.registration.token.ConfirmTokenRequest;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) throws MessagingException {
        return new ResponseEntity<>(registrationService.register(registrationRequest), HttpStatus.OK);
}
    @PostMapping("/confirm_token")
    public ResponseEntity<?> confirmToken(@RequestBody ConfirmTokenRequest confirmTokenRequest) {
        return new ResponseEntity<>(registrationService.confirmation(confirmTokenRequest), HttpStatus.OK);
    }


}
