package DevSGMA_PTC.SGMA_PTC.Models.ApiRespones;

public class APIResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public APIResponse() {
    }

    public APIResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter y Setter para data
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
