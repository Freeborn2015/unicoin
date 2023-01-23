package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.registration.token.ConfirmationToken;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.user.dto.request.DeleteRequest;
import africa.semicolon.unicoin.utils.RandomStringGenerator;
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
    @Override
    public String createAccount(User user) {
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user

        );

        confirmationTokenService.savedConfirmationToken(confirmationToken);


        return token;
    }


    @Override
    public void enableUser(String emailAddress) {
        userRepository.enable(emailAddress);
    }

    @Override
    public String deleteUserAccount(String email, DeleteRequest deleteRequest) {
        var foundUser = userRepository.findByEmailAddressIgnoreCase(email)
                .orElseThrow(()-> new RuntimeException("User with this"+ email +" does not exist"));
        if (!foundUser.getPassword().equals(deleteRequest.getPassword()))throw new RuntimeException("Incorrect password");
        StringBuilder randomValues = RandomStringGenerator.randomStringGenerator(8);
        foundUser.setEmailAddress("deleted" +email + randomValues);
        userRepository.save(foundUser);
        return "Deleted successfully";
    }
}
