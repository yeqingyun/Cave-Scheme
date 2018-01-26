package yeqy.cave.scheme.exception;

public class ParameterErrorException extends Exception {
    public ParameterErrorException() {
    }

    public ParameterErrorException(String message) {
        super(message);
    }

    public ParameterErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterErrorException(Throwable cause) {
        super(cause);
    }

    public ParameterErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
