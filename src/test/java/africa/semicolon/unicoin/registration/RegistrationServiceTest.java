package africa.semicolon.unicoin.registration;

import africa.semicolon.unicoin.email.EmailSender;
import africa.semicolon.unicoin.user.User;
import africa.semicolon.unicoin.user.UserRepository;
import africa.semicolon.unicoin.user.UserServiceImpl;
import africa.semicolon.unicoin.utils.MockUtils;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;

import static africa.semicolon.unicoin.utils.MockUtils.emailSenderMock;
import static africa.semicolon.unicoin.utils.MockUtils.userRepositoryMock;
import static org.mockito.ArgumentMatchers.any;
;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class RegistrationServiceTest {
    private final UserServiceImpl userServiceMock  =spy(MockUtils.userService());
    private RegistrationService registrationRService = new RegistrationService(
            userRepositoryMock,
            MockUtils.confirmationTokenServiceMock(),
            userServiceMock,
            emailSenderMock
    );





//    @BeforeEach
//    void setUp() {
//
//    }
    @Test void testRegister() throws MessagingException {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "benedict",
                "freeborn",
                "freebenny@gmail.com",
                "bene239087"

        );

        doReturn("7ad821a2-862d-44e0-914e-b1055d2e3514")
                .when(userServiceMock).createAccount(any(User.class));
        assertEquals(registrationRService.register(registrationRequest),"7ad821a2-862d-44e0-914e-b1055d2e3514");




    }
    @Test void confirmationToken(){

    }


    }
