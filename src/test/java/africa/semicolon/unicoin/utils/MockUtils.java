package africa.semicolon.unicoin.utils;

import africa.semicolon.unicoin.email.EmailSender;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.user.UserRepository;
import africa.semicolon.unicoin.user.UserServiceImpl;
import org.mockito.Mockito;

public class MockUtils {
        public static final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
        public static final EmailSender emailSenderMock = Mockito.mock(EmailSender.class);
       public static ConfirmationTokenService confirmationTokenServiceMock(){
           return new ConfirmationTokenService();
       }
        public  static UserServiceImpl userService(){
            return new UserServiceImpl(confirmationTokenServiceMock(),userRepositoryMock);
        }
}
