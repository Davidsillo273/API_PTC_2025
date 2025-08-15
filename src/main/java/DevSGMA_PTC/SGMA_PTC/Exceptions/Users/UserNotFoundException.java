package DevSGMA_PTC.SGMA_PTC.Exceptions.Users;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
