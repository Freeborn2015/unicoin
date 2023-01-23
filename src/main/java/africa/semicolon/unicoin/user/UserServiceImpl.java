package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.email.EmailSender;
import africa.semicolon.unicoin.exceptions.RegistrationException;
import africa.semicolon.unicoin.registration.token.ConfirmationToken;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.user.userRequest.LoginRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;


    @Override
    public String createAccount(User user) {
        return generateToken(userRepository.save(user));
    }


    @Override
    public void enableUser(String emailAddress) {
        userRepository.enable(emailAddress);
    }

    private String generateToken(User user){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user);
        confirmationTokenService.savedConfirmationToken(confirmationToken);
        return token;
    }

    @Override
    public String  login(LoginRequest loginRequest) throws MessagingException {
        User foundUser = userRepository
                .findByEmailAddressIgnoreCase(loginRequest.getEmailAddress())
                .orElseThrow(()->new RuntimeException("This email  does not exist"));
        if(!foundUser.getIsDisabled()) {
            if (foundUser.getPassword().equals(loginRequest.getPassword())) return "Login Successfully";
            else return "Invalid details";
        }
        emailSender.send(foundUser.getEmailAddress(), emailSender.buildEmail(foundUser.getFirstName(), generateToken(foundUser)));
        return "User has not confirmed email address,Token has been resent to provided email.";
    }
}
