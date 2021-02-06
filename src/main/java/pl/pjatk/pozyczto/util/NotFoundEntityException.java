package pl.pjatk.pozyczto.util;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException() {
    }

    public NotFoundEntityException(String message) {
        super(message);
    }

    public NotFoundEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundEntityException(Throwable cause) {
        super(cause);
    }

    public NotFoundEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
