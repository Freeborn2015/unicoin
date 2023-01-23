package africa.semicolon.unicoin.registration;

import africa.semicolon.unicoin.email.EmailSender;
import africa.semicolon.unicoin.exceptions.RegistrationException;
import africa.semicolon.unicoin.registration.token.*;
import africa.semicolon.unicoin.user.User;
import africa.semicolon.unicoin.user.UserRepository;
import africa.semicolon.unicoin.user.UserRole;
import africa.semicolon.unicoin.user.UserService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
   private UserRepository userRepository;
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    @Autowired
    private UserService userService;
    @Autowired
    EmailSender emailSender;
    public String register(RegistrationRequest registrationRequest) throws MessagingException {

        Boolean emailExists = userRepository
                .findByEmailAddressIgnoreCase(registrationRequest
                        .getEmailAddress()).isPresent();
        if (emailExists) throw new RegistrationException
                ("email address already exists");
        String token = userService.createAccount(new User(
                        registrationRequest.getEmailAddress(),
                        registrationRequest.getFirstName(),
                        registrationRequest.getLastName(),
                        UserRole.USER,
                        registrationRequest.getPassword()

                )

        );
        emailSender.send(registrationRequest.getEmailAddress(), emailSender.buildEmail(registrationRequest.getFirstName(), token));
return token;
    }
    public String confirmation(ConfirmTokenRequest confirmationToken){
        ConfirmationToken token = confirmationTokenService.getConfirmationToken(confirmationToken.getToken())
                .orElseThrow(()-> new IllegalStateException("token does not exist"));
        if(token.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new RegistrationException("token has expired");
        }
        confirmationTokenService.setConfirmAt(token.getToken());
        userService.enableUser(confirmationToken.getEmailAddress());
        return "confirmed";
    }



}
