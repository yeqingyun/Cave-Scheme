package yeqy.cave.scheme.type;

import yeqy.cave.scheme.analyze.Constant;
import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterException;
import yeqy.cave.scheme.exception.SyntaxException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yeqy on 2018/1/27.
 */
public class CaveFunction extends BaseType {
    private SExpression body;
    private List<String> parameter;
    private Environment env;

    public CaveFunction(SExpression body, List<String> parameter, Environment env) {
        this.body = body;
        this.parameter = parameter;
        this.env = env;
    }

    public Environment getEnv() {
        return env;
    }

    public SExpression getBody() {
        return body;
    }

    public List<String> getParameter() {
        return parameter;
    }

    public BaseType apply(BaseType... args) throws InvocationTargetException, ParameterException, MethodIsNotDefinedException, SyntaxException, IllegalAccessException {
        if (args.length != parameter.size()) {
            throw new SyntaxException("Exception: invalid syntax " + Arrays.deepToString(args));
        }
        int i = 0;
        for (String str : parameter) {
            env.setVar(str, args[i++]);
        }

        if (!Constant._if.getToken().equals(body.getChildren().get(0).getValue())) {
            return body.eval(env);
        } else {
            //尾递归优化 TODO
            if (((Boolean) body.getChildren().get(1).eval(env)).isValue()) {
                if ("".equals(body.getChildren().get(2).getValue())) {
                    SExpression loopExp = body.getChildren().get(2).getChildren().get(0);
                    if (env.findVar(loopExp.getValue()) == this) {//转化为循环
                        for (; ((Boolean) body.getChildren().get(1).eval(env)).isValue(); ) {
                            loopExcute(body.getChildren().get(2));
                        }
                        return body.getChildren().get(3).eval(env);
                    }
                }
                return body.eval(env);
            } else {
                if ("".equals(body.getChildren().get(3).getValue())) {
                    SExpression loopExp = body.getChildren().get(3).getChildren().get(0);
                    if (env.findVar(loopExp.getValue()) == this) {//转化为循环
                        for (; !((Boolean) body.getChildren().get(1).eval(env)).isValue(); ) {
                            loopExcute(body.getChildren().get(3));
                        }
                        return body.getChildren().get(2).eval(env);
                    }
                }
                return body.eval(env);
            }
        }

    }

    private void loopExcute(SExpression loopExp) throws InvocationTargetException, ParameterException, MethodIsNotDefinedException, SyntaxException, IllegalAccessException {

        for (int i = 1; i < loopExp.getChildren().size(); i++) {
            SExpression child = loopExp.getChildren().get(i);
            String varName = findVarName(child);
            if (varName != null)
                env.setVar(varName, child.eval(env));
        }
    }

    private String findVarName(SExpression exp) {
        for (SExpression child : exp.getChildren()) {
            if (env.findCurrentVar(child.getValue()) != null) {
                return child.getValue();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (String par : parameter) {
            sb.append(par).append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append(") ").append(body.toString());
        return sb.toString();
    }
}
