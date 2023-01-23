package africa.semicolon.unicoin.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Entity
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "required")
    @NotEmpty(message = "required")
    @Email(message = "required")
    private String emailAddress;
    @NotNull(message = "required")
    @NotEmpty(message = "required")
    private String firstName;
    @NotNull(message = "required")
    @NotEmpty(message = "required")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean isDisabled = true;
    private String password;

    public User( String emailAddress, String firstName,
                 String lastName, UserRole userRole,
                 String password) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.password = password;
    }
}
