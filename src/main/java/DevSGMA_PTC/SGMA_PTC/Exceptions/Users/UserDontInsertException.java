package DevSGMA_PTC.SGMA_PTC.Exceptions.Users;

public class UserDontInsertException extends RuntimeException {
    public UserDontInsertException(String message) {
        super(message);
    }
}
