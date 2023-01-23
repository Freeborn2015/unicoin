//package africa.semicolon.unicoin.registration;
//import africa.semicolon.unicoin.UnicoinApplication;
//import africa.semicolon.unicoin.user.User;
//import africa.semicolon.unicoin.user.UserServiceImpl;
//import africa.semicolon.unicoin.utils.MockUtils;
//import jakarta.mail.MessagingException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;
//
//import static africa.semicolon.unicoin.utils.MockUtils.emailSenderMock;
//import static africa.semicolon.unicoin.utils.MockUtils.userRepositoryMock;
//import static org.mockito.ArgumentMatchers.any;
//;import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.spy;
//
//@SpringBootTest (classes = UnicoinApplication.class)
//class RegistrationServiceTest {
//    private final UserServiceImpl userServiceMock  =spy(MockUtils.userService());
//    private RegistrationService registrationRService = new RegistrationService(
//            userRepositoryMock,
//            MockUtils.confirmationTokenServiceMock(),
//            userServiceMock,
//            emailSenderMock
//    );

//    @Autowired
//    RegistrationService registrationService;
//
//    RegistrationRequest registrationRequest;
//
//    @BeforeEach
//    public void setUp() {
//        registrationRequest = new RegistrationRequest("Jennifer", "Musah", "jenny@gmail.com", "KUMfghety';3425");
//    }
//
//    @Test
//    public void registerTest() throws MessagingException {
//        String token = registrationService.register(registrationRequest);
//        assertNotNull(token);
//    }
//}