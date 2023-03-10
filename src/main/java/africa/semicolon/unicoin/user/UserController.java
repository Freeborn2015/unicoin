package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.user.dto.request.DeleteRequest;
import africa.semicolon.unicoin.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/{emailAddress}")
    public ResponseEntity<?> deleteUser(@PathVariable("emailAddress") String emailAddress,
                                        @RequestBody DeleteRequest deleteRequest, HttpServletRequest httpServletRequest){
        var deleteUser = userService.deleteUserAccount(emailAddress, deleteRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .data(deleteUser)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
