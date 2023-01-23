package africa.semicolon.unicoin.login;

import africa.semicolon.unicoin.user.User;
import africa.semicolon.unicoin.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public String  login(LoginRequest loginRequest) {
        User foundUser = userRepository
                .findByEmailAddressIgnoreCase(loginRequest.getEmailAddress())
                .orElseThrow(()->new RuntimeException("This email  does not exist"));
        if(foundUser.getIsDisabled() != null) {
            if (foundUser.getPassword().equals(loginRequest.getPassword())) return "Login Successfully";
            else return "Incorrect password";
        }
        return "User has not confirmed token";
    }

}
