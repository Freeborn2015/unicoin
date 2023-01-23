package africa.semicolon.unicoin.user;


import africa.semicolon.unicoin.user.dto.request.DeleteRequest;

public interface UserService {
    public String createAccount(User user);

    void enableUser(String emailAddress);
    String deleteUserAccount(String emailAddress, DeleteRequest deleteRequest);
}
