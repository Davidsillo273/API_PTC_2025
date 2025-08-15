package DevSGMA_PTC.SGMA_PTC.Exceptions.Users;

public class EmailUserDuplicateException extends RuntimeException {
    public EmailUserDuplicateException(String message) {
        super(message);
    }
}
