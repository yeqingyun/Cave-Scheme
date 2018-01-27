package yeqy.cave.scheme.paser;

import yeqy.cave.scheme.analyze.Constant;
import yeqy.cave.scheme.analyze.Feature;
import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterException;
import yeqy.cave.scheme.exception.SyntaxException;
import yeqy.cave.scheme.keyword.Define;
import yeqy.cave.scheme.keyword.If;
import yeqy.cave.scheme.keyword.Lambda;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;
import yeqy.cave.scheme.type.*;
import yeqy.cave.scheme.type.Boolean;
import yeqy.cave.scheme.type.Number;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yeqy on 2018/1/26.
 */
public class Apply {
    public static BaseType signleEvalApply(SExpression sExpression, Environment env) throws SyntaxException {
        String unknownVar = sExpression.getValue();
        if (unknownVar.matches("(-)?\\d+")) {
            return new Number(Integer.valueOf(unknownVar));
        } else if (unknownVar.equals(Constant._true.getToken()) || unknownVar.equals(Constant._false.getToken())) {
            return new Boolean(java.lang.Boolean.valueOf(unknownVar));
        } else if (unknownVar.equals(Constant.nil.getToken())) {
            return new Boolean(java.lang.Boolean.valueOf(unknownVar));
        } else if (unknownVar.startsWith("\"") && unknownVar.endsWith("\"")) {
            return new CaveString(unknownVar);
        } else {
            BaseType data = env.findVar(unknownVar);
            Constant ct;
            if (data != null) {
                return data;
            } else if ((ct = Constant.chooseKey(unknownVar)) != null) {
                if (Feature.keywords.contains(ct))
                    return new CaveString("Exception: invalid syntax apply");
                return new CaveString("#<procedure abs>");
            } else {
                throw new SyntaxException(unknownVar == null || "".equals(unknownVar) ? "Exception: invalid syntax" : unknownVar + " is not defined");
            }
        }
    }


    public static BaseType keyWordsApply(SExpression sExpression, Environment env) throws MethodIsNotDefinedException, InvocationTargetException, ParameterException, IllegalAccessException, SyntaxException {
        String unknownKeyWord = sExpression.getChildren().get(0).getValue();
        if (Constant._if.getToken().equals(unknownKeyWord)) {
            return If.apply(sExpression, env);
        } else if (Constant.lambda.getToken().equals(unknownKeyWord)) {
            return Lambda.apply(sExpression, env);//包含闭包
        } else if (Constant.define.getToken().equals(unknownKeyWord)) {
            Define.apply(sExpression, env);
        }
        return null;
    }

    public static BaseType functionApply(SExpression sExpression, Environment env) throws MethodIsNotDefinedException, InvocationTargetException, IllegalAccessException, ParameterException, SyntaxException {
        String unknownFunction = sExpression.getChildren().get(0).getValue();

        String function = Feature.functions.get(Constant.chooseKey(unknownFunction));

        if (function == null) {//自定义函数
            BaseType func = env.findVar(unknownFunction);
            if (func == null) {
                throw new SyntaxException(unknownFunction == null || "".equals(unknownFunction) ? "Exception: invalid syntax" : unknownFunction + " is not defined");
            }
            return ((CaveFunction) func).apply(buildParam(sExpression, env));
        } else {//系统函数
            String ClassName = function.substring(0, function.lastIndexOf("."));
            String methodName = function.substring(function.lastIndexOf(".") + 1);
            try {
                Method method = Class.forName(ClassName).getMethod(methodName, BaseType[].class);
                BaseType[] params = buildParam(sExpression, env);
                return (BaseType) method.invoke(null, new Object[]{params});//应用序apply
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                throw new MethodIsNotDefinedException(unknownFunction == null || "".equals(unknownFunction) ? "Exception: invalid syntax" : unknownFunction + " is not defined");
            }
        }
    }

    public static BaseType lambdaApply(SExpression exp, Environment env) throws IllegalAccessException, ParameterException, MethodIsNotDefinedException, SyntaxException, InvocationTargetException {
        CaveFunction function = Lambda.apply(exp.getChildren().get(0), env);
        return function.apply(buildParam(1, exp, env));
    }

    public static BaseType[] buildParam(SExpression root, Environment env) throws IllegalAccessException, MethodIsNotDefinedException, InvocationTargetException, ParameterException, SyntaxException {
        //目前采用应用序
        List<SExpression> params = root.getChildren().subList(1, root.getChildren().size());
        BaseType[] expressions = new BaseType[params.size()];
        int i = 0;
        for (SExpression s : params) {
            expressions[i++] = s.eval(env);
        }
        return expressions;

    }

    public static BaseType[] buildParam(int begin, SExpression root, Environment env) throws IllegalAccessException, MethodIsNotDefinedException, InvocationTargetException, ParameterException, SyntaxException {
        //目前采用应用序
        List<SExpression> params = root.getChildren().subList(begin, root.getChildren().size());
        BaseType[] expressions = new BaseType[params.size()];
        int i = 0;
        for (SExpression s : params) {
            expressions[i++] = s.eval(env);
        }
        return expressions;
    }

}
