package yeqy.cave.scheme.keyword;

import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterException;
import yeqy.cave.scheme.exception.SyntaxException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;

import java.lang.reflect.InvocationTargetException;

public class Define {

    public static void apply(SExpression exp, Environment env) throws InvocationTargetException, ParameterException, MethodIsNotDefinedException, IllegalAccessException, SyntaxException {
        if (exp.getChildren().size() > 3)
            throw new SyntaxException("Exception: invalid syntax " + exp);
        else {
            String var = exp.getChildren().get(1).getValue();
            env.setVar(var, exp.getChildren().get(2).eval(env));
        }
    }
}
