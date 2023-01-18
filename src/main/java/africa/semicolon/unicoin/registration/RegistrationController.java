package africa.semicolon.unicoin.registration;

import africa.semicolon.unicoin.registration.token.ConfirmTokenRequest;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenRequest;
import africa.semicolon.unicoin.utils.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest,
                                                HttpServletRequest httpServletRequest)
            throws MessagingException {
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .data(registrationService.register(registrationRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

    }

    public ResponseEntity<?> confirmToken(@RequestBody ConfirmTokenRequest
                                                  confirmTokenRequest,
                                          HttpServletRequest httpServletRequest
    ) {
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(registrationService.confirmation(confirmTokenRequest))
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
