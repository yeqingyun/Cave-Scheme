package yeqy.cave.scheme.exception;

/**
 * Created by yeqy on 2018/1/26.
 */
public class SyntaxError extends Exception {
    String msg;

    public SyntaxError(String msg) {
        this.msg = msg;
    }

    public SyntaxError(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public SyntaxError(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }
}
