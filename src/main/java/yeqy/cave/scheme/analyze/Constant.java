package yeqy.cave.scheme.analyze;

/**
 * Created by yeqy on 2018/1/26.
 */
public enum Constant {
    left_braces("("),
    right_braces(")"),
    plus("+"),
    divide("/"),
    multiply("*"),
    reduce("-"),
    lambda("lambda"),
    define("define"),
    _if("if"),
    _true("true"),
    _false("false"),
    nil("nil");

    private String token;

    Constant(String token) {
        this.token = token;
    }

    public static Constant chooseKey(String token) {
        switch (token) {
            case "(":
                return left_braces;
            case ")":
                return right_braces;
            case "+":
                return plus;
            case "/":
                return divide;
            case "*":
                return multiply;
            case "-":
                return reduce;
            case "lambda":
                return lambda;
            case "define":
                return define;
            case "if":
                return _if;
            default:
                return null;
        }
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
