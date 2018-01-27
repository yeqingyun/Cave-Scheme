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
    equal("="),
    equals("equals?"),
    or("or"),
    more_than(">"),
    less_than("<"),
    not("not"),
    and("and"),
    nil("nil");

    private String token;

    Constant(String token) {
        this.token = token;
    }

    public static Constant chooseKey(String token) {
        for (Constant constant : Constant.values()) {
            if (constant.getToken().equals(token))
                return constant;
        }
        return null;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
