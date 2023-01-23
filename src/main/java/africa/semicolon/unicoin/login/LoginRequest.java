package africa.semicolon.unicoin.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailAddress;
    private String password;
}
