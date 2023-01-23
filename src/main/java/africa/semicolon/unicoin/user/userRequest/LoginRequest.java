package africa.semicolon.unicoin.user.userRequest;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailAddress;
    private String password;
}
