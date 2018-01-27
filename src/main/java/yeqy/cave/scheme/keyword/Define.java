package yeqy.cave.scheme.keyword;

import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterException;
import yeqy.cave.scheme.exception.SyntaxException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;
import yeqy.cave.scheme.type.CaveFunction;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Define {

    public static void apply(SExpression exp, Environment env) throws InvocationTargetException, ParameterException, MethodIsNotDefinedException, IllegalAccessException, SyntaxException {
        if (exp.getChildren().size() > 3)
            throw new SyntaxException("Exception: invalid syntax " + exp);
        else {
            String var = exp.getChildren().get(1).getValue();
            if ("".equals(var)) {
                SExpression argument = exp.getChildren().get(1);
                if (argument.getChildren().size() < 2) {
                    throw new SyntaxException("Exception: invalid syntax " + exp);
                }

                String functionName = argument.getChildren().get(0).getValue();

                List<String> params = new ArrayList<>();
                for (int i = 1; i < argument.getChildren().size(); i++) {
                    params.add(argument.getChildren().get(i).getValue());
                }

                env.setVar(functionName, new CaveFunction(exp.getChildren().get(2), params, new Environment(env)));
            } else {
                env.setVar(var, exp.getChildren().get(2).eval(env));
            }
        }
    }
}
