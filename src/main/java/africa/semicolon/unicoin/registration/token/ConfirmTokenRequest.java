package africa.semicolon.unicoin.registration.token;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfirmTokenRequest {
    @NotNull(message = "should not be empty")
    private String token;
    @NotNull(message = "should not be empty")
    private String emailAddress;
}
