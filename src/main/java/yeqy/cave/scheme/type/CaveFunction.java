package yeqy.cave.scheme.type;

import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterException;
import yeqy.cave.scheme.exception.SyntaxException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by yeqy on 2018/1/27.
 */
public class CaveFunction extends BaseType {
    private SExpression body;
    private Set<String> parameter;
    private Environment env;

    public CaveFunction(SExpression body, Set<String> parameter, Environment env) {
        this.body = body;
        this.parameter = parameter;
        this.env = env;
    }

    public SExpression getBody() {
        return body;
    }

    public Set<String> getParameter() {
        return parameter;
    }

    public Environment getEnv() {
        return env;
    }

    public BaseType apply(BaseType... args) throws InvocationTargetException, ParameterException, MethodIsNotDefinedException, SyntaxException, IllegalAccessException {
        if (args.length != parameter.size()) {
            throw new SyntaxException("Exception: invalid syntax " + Arrays.deepToString(args));
        }
        int i = 0;
        for (String str : parameter) {
            env.setVar(str, args[i++]);
        }
        return body.eval(env);
    }
}
