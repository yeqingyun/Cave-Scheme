package yeqy.cave.scheme.exception;

/**
 * Created by yeqy on 2018/1/26.
 */
public class SyntaxException extends Exception {
    public SyntaxException() {
    }

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public SyntaxException(Throwable cause) {
        super(cause);
    }

    public SyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
