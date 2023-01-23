package africa.semicolon.unicoin.user;


import africa.semicolon.unicoin.user.userRequest.LoginRequest;
import jakarta.mail.MessagingException;

public interface UserService {
    public String createAccount(User user);

    void enableUser(String emailAddress);

    String  login(LoginRequest loginRequest) throws MessagingException;
}
