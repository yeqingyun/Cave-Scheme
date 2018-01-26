package yeqy.cave.scheme.paser;

import yeqy.cave.scheme.analyze.Constant;
import yeqy.cave.scheme.analyze.Feature;
import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;
import yeqy.cave.scheme.type.BaseType;
import yeqy.cave.scheme.type.Boolean;
import yeqy.cave.scheme.type.CaveString;
import yeqy.cave.scheme.type.Number;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yeqy on 2018/1/26.
 */
public class Apply {
    public static BaseType signleEvalApply(SExpression sExpression, Environment env) {
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
                    return new CaveString("Exception: invalid syntax define");
                return new CaveString("#<procedure abs>");
            } else {
                return new CaveString(unknownVar + " is not defined");
            }
        }
    }


    public static void keyWordsApply(SExpression sExpression, Environment env) {

    }

    public static BaseType functionApply(SExpression sExpression, Environment env) throws MethodIsNotDefinedException, InvocationTargetException, IllegalAccessException {
        String unknownFunction = sExpression.getChildren().get(0).getValue();

        String function = Feature.functions.get(Constant.chooseKey(unknownFunction));

        if (function == null) {//自定义函数
            BaseType data = env.findVar(unknownFunction);
            if (data == null) {
                return new CaveString(unknownFunction + " is not defined");
            }
            return null;
            //TODO
        } else {//默认函数
            String ClassName = function.substring(0, function.lastIndexOf("."));
            String methodName = function.substring(function.lastIndexOf(".") + 1);
            try {
                Method method = Class.forName(ClassName).getMethod(methodName, BaseType[].class);
                BaseType[] params = buildParam(sExpression, env);
                return (BaseType) method.invoke(null, new Object[]{params});//应用序
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                throw new MethodIsNotDefinedException(unknownFunction + "is not defined");
            }
        }
    }

    public static BaseType[] buildParam(SExpression root, Environment env) throws IllegalAccessException, MethodIsNotDefinedException, InvocationTargetException {//目前采用应用序
        List<SExpression> params = root.getChildren().subList(1, root.getChildren().size());
        BaseType[] expressions = new BaseType[params.size()];
        int i = 0;
        for (SExpression s : params) {
            expressions[i++] = s.eval(env);
        }
        return expressions;
    }
}
