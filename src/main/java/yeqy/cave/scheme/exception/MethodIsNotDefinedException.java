package yeqy.cave.scheme.exception;

/**
 * Created by yeqy on 2018/1/26.
 */
public class MethodIsNotDefinedException extends Exception {

    public MethodIsNotDefinedException() {
    }

    public MethodIsNotDefinedException(String message) {
        super(message);
    }

    public MethodIsNotDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
}
