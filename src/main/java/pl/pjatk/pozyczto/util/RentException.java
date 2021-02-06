package pl.pjatk.pozyczto.util;

public class RentException extends RuntimeException{
    public RentException() {
    }

    public RentException(String message) {
        super(message);
    }

    public RentException(String message, Throwable cause) {
        super(message, cause);
    }

    public RentException(Throwable cause) {
        super(cause);
    }

    public RentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
