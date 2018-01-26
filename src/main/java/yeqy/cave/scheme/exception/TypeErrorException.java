package yeqy.cave.scheme.exception;

/**
 * Created by yeqy on 2018/1/26.
 */
public class TypeErrorException extends Exception {
    public TypeErrorException() {
    }

    public TypeErrorException(String message) {
        super(message);
    }

    public TypeErrorException(Throwable cause) {
        super(cause);
    }
}
